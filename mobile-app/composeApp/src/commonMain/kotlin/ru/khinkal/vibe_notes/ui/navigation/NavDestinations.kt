package ru.khinkal.vibe_notes.ui.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import ru.khinkal.vibe_notes.data.model.Note

@Serializable
sealed interface VibeDestination : NavKey {
    @Serializable
    data object Splash : VibeDestination

    @Serializable
    data object Auth : VibeDestination

    @Serializable
    data object Notes : VibeDestination

    @Serializable
    data class EditNote(val note: Note? = null) : VibeDestination
}

val NavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(VibeDestination.Auth::class, VibeDestination.Auth.serializer())
            subclass(VibeDestination.Notes::class, VibeDestination.Notes.serializer())
            subclass(VibeDestination.EditNote::class, VibeDestination.EditNote.serializer())
            subclass(VibeDestination.Splash::class, VibeDestination.Splash.serializer())
        }
    }
}
