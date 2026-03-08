package ru.khinkal.vibe_notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.model.User
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.repository.AuthRepository
import ru.khinkal.vibe_notes.data.repository.NotesRepository
import ru.khinkal.vibe_notes.data.repository.UserRepository

data class NotesUiState(
    val user: User? = null,
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class NotesViewModel(
    private val notesRepository: NotesRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(NotesUiState())
    val state: StateFlow<NotesUiState> = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val userResult = userRepository.me()
            val notesResult = notesRepository.getNotes()

            _state.update { current ->
                val nextUser = when (userResult) {
                    is ApiResult.Success -> userResult.value
                    is ApiResult.Error -> current.user
                }
                val nextNotes = when (notesResult) {
                    is ApiResult.Success -> notesResult.value
                    is ApiResult.Error -> current.notes
                }
                val errorMessage = when {
                    notesResult is ApiResult.Error -> notesResult.message
                    userResult is ApiResult.Error -> userResult.message
                    else -> null
                }
                current.copy(
                    user = nextUser,
                    notes = nextNotes,
                    isLoading = false,
                    errorMessage = errorMessage,
                )
            }
        }
    }

    fun onNoteUpserted(note: Note) {
        _state.update { current ->
            val index = current.notes.indexOfFirst { it.id == note.id }
            val updatedNotes = if (index >= 0) {
                current.notes.toMutableList().apply { this[index] = note }
            } else {
                listOf(note) + current.notes
            }
            current.copy(notes = updatedNotes)
        }
    }

    fun onNoteDeleted(noteId: String) {
        _state.update { current ->
            current.copy(notes = current.notes.filterNot { it.id == noteId })
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
