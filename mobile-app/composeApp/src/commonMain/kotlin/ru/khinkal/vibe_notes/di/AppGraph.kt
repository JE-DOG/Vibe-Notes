package ru.khinkal.vibe_notes.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Includes
import ru.khinkal.vibe_notes.data.repository.AuthRepository
import ru.khinkal.vibe_notes.data.repository.NotesRepository
import ru.khinkal.vibe_notes.ui.viewmodel.AuthViewModelFactory
import ru.khinkal.vibe_notes.ui.viewmodel.NoteEditorViewModelFactory
import ru.khinkal.vibe_notes.ui.viewmodel.NotesViewModelFactory

@DependencyGraph(
    scope = AppScope::class,
    bindingContainers = [
        CommonBindings::class,
        ViewModelBindings::class,
    ],
)
interface AppGraph {

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(
            @Includes platformBindings: PlatformBindings,
        ): AppGraph
    }

    val authRepository: AuthRepository
    val notesRepository: NotesRepository

    val authViewModelFactory: AuthViewModelFactory
    val notesViewModelFactory: NotesViewModelFactory
    val noteEditorViewModelFactory: NoteEditorViewModelFactory
}
