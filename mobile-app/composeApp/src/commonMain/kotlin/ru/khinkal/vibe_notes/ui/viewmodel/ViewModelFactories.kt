package ru.khinkal.vibe_notes.ui.viewmodel

fun interface AuthViewModelFactory {
    fun create(): AuthViewModel
}

fun interface NotesViewModelFactory {
    fun create(): NotesViewModel
}

fun interface NoteEditorViewModelFactory {
    fun create(): NoteEditorViewModel
}
