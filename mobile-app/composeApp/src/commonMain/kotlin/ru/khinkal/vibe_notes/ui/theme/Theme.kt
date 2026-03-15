package ru.khinkal.vibe_notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = Ink,
    inversePrimary = blend(Teal, Sand, 0.45f),
    secondary = Coral,
    onSecondary = Ink,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = Ink,
    tertiary = Moss,
    onTertiary = Color.White,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = Ink,
    background = Sand,
    onBackground = Ink,
    surface = LightSurface,
    onSurface = Ink,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = Ink,
    surfaceTint = Teal,
    inverseSurface = darken(Ink, 0.2f),
    inverseOnSurface = Sand,
    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    scrim = Color.Black.copy(alpha = 0.4f),
    error = LightError,
    onError = Color.White,
    errorContainer = LightErrorContainer,
    onErrorContainer = Ink,
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Ink,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = Sand,
    inversePrimary = Teal,
    secondary = DarkSecondary,
    onSecondary = Ink,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = Sand,
    tertiary = DarkTertiary,
    onTertiary = Ink,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = Sand,
    background = DarkBackground,
    onBackground = Sand,
    surface = DarkSurface,
    onSurface = Sand,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Sand.copy(alpha = 0.82f),
    surfaceTint = DarkPrimary,
    inverseSurface = Sand,
    inverseOnSurface = Ink,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    scrim = Color.Black.copy(alpha = 0.6f),
    error = DarkError,
    onError = Ink,
    errorContainer = DarkErrorContainer,
    onErrorContainer = Sand,
)

@Composable
fun VibeNotesTheme(content: @Composable () -> Unit) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colorScheme = if (isSystemInDarkTheme) DarkColors else LightColors
    SetStatusBarAppearanceEffect(
        useForLightAppearance = !isSystemInDarkTheme,
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = VibeTypography,
        content = content,
    )
}

@Composable
expect fun SetStatusBarAppearanceEffect(useForLightAppearance: Boolean)
