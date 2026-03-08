package ru.khinkal.vibe_notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import ru.khinkal.vibe_notes.di.LocalAppGraph
import ru.khinkal.vibe_notes.di.PlatformContext
import ru.khinkal.vibe_notes.di.rememberAppGraph
import ru.khinkal.vibe_notes.ui.navigation.NavConfig
import ru.khinkal.vibe_notes.ui.navigation.VibeDestination
import ru.khinkal.vibe_notes.ui.screens.AuthScreen
import ru.khinkal.vibe_notes.ui.screens.NoteEditorScreen
import ru.khinkal.vibe_notes.ui.screens.NotesScreen
import ru.khinkal.vibe_notes.ui.theme.VibeNotesTheme
import ru.khinkal.vibe_notes.ui.viewmodel.AuthViewModel
import ru.khinkal.vibe_notes.ui.viewmodel.MetroViewModelFactory
import ru.khinkal.vibe_notes.ui.viewmodel.NoteEditorEvent
import ru.khinkal.vibe_notes.ui.viewmodel.NoteEditorViewModel
import ru.khinkal.vibe_notes.ui.viewmodel.NotesViewModel

@Composable
fun App(platformContext: PlatformContext) {
    val appGraph = rememberAppGraph(platformContext)
    VibeNotesTheme {
        CompositionLocalProvider(LocalAppGraph provides appGraph) {
            AppRoot()
        }
    }
}

@Composable
private fun AppRoot() {
    val appGraph = LocalAppGraph.current
    val backStack = rememberNavBackStack(NavConfig, VibeDestination.Auth)
    val isSignedIn by appGraph.authRepository.isSignedIn.collectAsState(false)
    var notesViewModel by remember { mutableStateOf<NotesViewModel?>(null) }

    LaunchedEffect(isSignedIn) {
        val destination = if (isSignedIn) VibeDestination.Notes else VibeDestination.Auth
        val current = backStack.lastOrNull()
        if (current != destination) {
            backStack.clear()
            backStack.add(destination)
        }
        if (!isSignedIn) {
            notesViewModel = null
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<VibeDestination.Auth> {
                val viewModel: AuthViewModel = viewModel(
                    factory = MetroViewModelFactory(appGraph.authViewModelFactory::create),
                )
                val state by viewModel.state.collectAsState()
                AuthScreen(
                    state = state,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    onSubmit = viewModel::submit,
                    onToggleMode = viewModel::toggleMode,
                )
            }
            entry<VibeDestination.Notes> {
                val viewModel: NotesViewModel = viewModel(
                    factory = MetroViewModelFactory(appGraph.notesViewModelFactory::create),
                )
                val state by viewModel.state.collectAsState()
                LaunchedEffect(viewModel) {
                    notesViewModel = viewModel
                }
                NotesScreen(
                    state = state,
                    onRefresh = viewModel::refresh,
                    onAddNote = { backStack.add(VibeDestination.EditNote()) },
                    onNoteClick = { note -> backStack.add(VibeDestination.EditNote(note)) },
                    onLogout = viewModel::logout,
                )
            }
            entry<VibeDestination.EditNote> { key ->
                val viewModel: NoteEditorViewModel = viewModel(
                    factory = MetroViewModelFactory(appGraph.noteEditorViewModelFactory::create),
                )
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key.note?.id) {
                    viewModel.setNote(key.note)
                }

                LaunchedEffect(viewModel.events) {
                    viewModel.events.collect { event ->
                        when (event) {
                            is NoteEditorEvent.Saved -> {
                                notesViewModel?.onNoteUpserted(event.note)
                                backStack.removeLastOrNull()
                            }
                            is NoteEditorEvent.Deleted -> {
                                notesViewModel?.onNoteDeleted(event.noteId)
                                backStack.removeLastOrNull()
                            }
                        }
                    }
                }

                NoteEditorScreen(
                    state = state,
                    onTitleChange = viewModel::onTitleChange,
                    onContentChange = viewModel::onContentChange,
                    onSave = viewModel::save,
                    onDelete = viewModel::delete,
                    onBack = { backStack.removeLastOrNull() },
                )
            }
        },
    )
}
