package ru.khinkal.vibe_notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.repository.NotesRepository

data class NoteEditorUiState(
    val noteId: Int? = null,
    val title: String = "",
    val content: String = "",
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface NoteEditorEvent {
    data object Saved : NoteEditorEvent
}

class NoteEditorViewModel(
    private val notesRepository: NotesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(NoteEditorUiState())
    val state: StateFlow<NoteEditorUiState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<NoteEditorEvent>()
    val events: SharedFlow<NoteEditorEvent> = _events.asSharedFlow()

    fun setNote(note: Note?) {
        if (note == null) return
        if (state.value.noteId == note.id) return
        _state.update {
            it.copy(
                noteId = note.id,
                title = note.title,
                content = note.content,
            )
        }
    }

    fun onTitleChange(value: String) {
        _state.update { it.copy(title = value, errorMessage = null) }
    }

    fun onContentChange(value: String) {
        _state.update { it.copy(content = value, errorMessage = null) }
    }

    fun save() {
        val current = state.value
        if (current.noteId != null) {
            _state.update { it.copy(errorMessage = "Editing existing notes is not supported.") }
            return
        }
        if (current.title.isBlank() || current.content.isBlank()) {
            _state.update { it.copy(errorMessage = "Title and content are required.") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true, errorMessage = null) }
            val result = notesRepository.create(current.title.trim(), current.content.trim())
            when (result) {
                is ApiResult.Success -> {
                    _state.update { it.copy(isSaving = false) }
                    _events.emit(NoteEditorEvent.Saved)
                }

                is ApiResult.Error -> {
                    _state.update {
                        it.copy(isSaving = false, errorMessage = result.message)
                    }
                }
            }
        }
    }
}
