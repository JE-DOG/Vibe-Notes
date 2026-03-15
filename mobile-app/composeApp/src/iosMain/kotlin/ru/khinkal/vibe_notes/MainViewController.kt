package ru.khinkal.vibe_notes

import androidx.compose.ui.window.ComposeUIViewController
import ru.khinkal.vibe_notes.di.PlatformContext

fun MainViewController() = ComposeUIViewController { App(platformContext = PlatformContext()) }
