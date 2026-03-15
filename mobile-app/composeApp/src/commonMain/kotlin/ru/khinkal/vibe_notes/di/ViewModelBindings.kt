package ru.khinkal.vibe_notes.di

import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import ru.khinkal.vibe_notes.data.repository.AuthRepository
import ru.khinkal.vibe_notes.data.repository.NotesRepository
import ru.khinkal.vibe_notes.ui.viewmodel.AuthViewModel
import ru.khinkal.vibe_notes.ui.viewmodel.AuthViewModelFactory
import ru.khinkal.vibe_notes.ui.viewmodel.NoteEditorViewModel
import ru.khinkal.vibe_notes.ui.viewmodel.NoteEditorViewModelFactory
import ru.khinkal.vibe_notes.ui.viewmodel.NotesViewModel
import ru.khinkal.vibe_notes.ui.viewmodel.NotesViewModelFactory

@BindingContainer
class ViewModelBindings {
    @Provides
    fun provideAuthViewModelFactory(
        authRepository: AuthRepository,
    ): AuthViewModelFactory = AuthViewModelFactory {
        AuthViewModel(authRepository)
    }

    @Provides
    fun provideNotesViewModelFactory(
        notesRepository: NotesRepository,
        authRepository: AuthRepository,
    ): NotesViewModelFactory = NotesViewModelFactory {
        NotesViewModel(notesRepository, authRepository)
    }

    @Provides
    fun provideNoteEditorViewModelFactory(
        notesRepository: NotesRepository,
    ): NoteEditorViewModelFactory = NoteEditorViewModelFactory {
        NoteEditorViewModel(notesRepository)
    }
}
