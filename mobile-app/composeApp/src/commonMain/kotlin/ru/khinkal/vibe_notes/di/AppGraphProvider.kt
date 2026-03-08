package ru.khinkal.vibe_notes.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import dev.zacsweers.metro.createGraphFactory

val LocalAppGraph = staticCompositionLocalOf<AppGraph> {
    error("AppGraph is not provided")
}

fun createAppGraph(platformContext: PlatformContext): AppGraph {
    val factory = createGraphFactory<AppGraph.Factory>()
    return factory.create(PlatformBindings(platformContext))
}

@Composable
fun rememberAppGraph(platformContext: PlatformContext): AppGraph =
    remember(platformContext) { createAppGraph(platformContext) }
