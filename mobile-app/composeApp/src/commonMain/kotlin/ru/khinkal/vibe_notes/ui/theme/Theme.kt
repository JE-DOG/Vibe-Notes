package ru.khinkal.vibe_notes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    secondary = Coral,
    onSecondary = Ink,
    tertiary = Moss,
    onTertiary = Color.White,
    background = Sand,
    onBackground = Ink,
    surface = SurfaceWarm,
    onSurface = Ink,
    surfaceVariant = Sky,
    onSurfaceVariant = Ink,
    outline = Ink.copy(alpha = 0.2f),
)

@Composable
fun VibeNotesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = VibeTypography,
        content = content,
    )
}
