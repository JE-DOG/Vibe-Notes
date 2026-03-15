package ru.khinkal.vibe_notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.khinkal.vibe_notes.data.debug.isDebugBuild
import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.repository.AuthRepository
import ru.khinkal.vibe_notes.data.repository.NotesRepository

data class NotesUiState(
    val login: String? = null,
    val notes: List<Note> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class NotesViewModel(
    private val notesRepository: NotesRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(NotesUiState())
    val state: StateFlow<NotesUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.loginFlow.collect { login ->
                _state.update { it.copy(login = login) }
            }
        }
        loadInitial()
    }

    fun refresh() {
        load(isRefreshing = true)
    }

    private fun loadInitial() {
        load(isRefreshing = false)
    }

    private fun load(isRefreshing: Boolean) {
        viewModelScope.launch {
            _state.update {
                if (isRefreshing) {
                    it.copy(isRefreshing = true, errorMessage = null)
                } else {
                    it.copy(isLoading = true, errorMessage = null)
                }
            }
            val notesResult = notesRepository.getNotes()

            _state.update { current ->
                val resolvedNotes = when (notesResult) {
                    is ApiResult.Success -> notesResult.value
                    is ApiResult.Error -> current.notes
                }
                val nextNotes = if (
                    notesResult is ApiResult.Error &&
                    resolvedNotes.isEmpty() &&
                    isDebugBuild
                ) {
                    demoNotes()
                } else {
                    resolvedNotes
                }
                val errorMessage = when {
                    notesResult is ApiResult.Error -> notesResult.message
                    else -> null
                }
                current.copy(
                    notes = nextNotes,
                    isRefreshing = false,
                    isLoading = if (isRefreshing) current.isLoading else false,
                    errorMessage = errorMessage,
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    private fun demoNotes(): List<Note> = listOf(
        Note(
            id = 1,
            userId = 0,
            title = "Morning thoughts",
            content = "Soft light and a quiet plan for the day. Keep it simple.",
        ),
        Note(
            id = 2,
            userId = 0,
            title = "Tiny checklist",
            content = "1. Brew coffee\n2. Tidy workspace\n3. Ship one small thing",
        ),
        Note(
            id = 3,
            userId = 0,
            title = "Idea seed",
            content = "A journaling flow that feels like a conversation with yourself.",
        ),
    )
}
