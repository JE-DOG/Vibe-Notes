package ru.khinkal.vibe_notes.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp


fun blend(base: Color, tint: Color, amount: Float): Color =
    lerp(base, tint, amount.coerceIn(0f, 1f))

fun darken(color: Color, amount: Float): Color =
    lerp(color, Color.Black, amount.coerceIn(0f, 1f))

val Ink = Color(0xFF1D1B16)
val Sand = Color(0xFFF7F2EC)
val SurfaceWarm = Color(0xFFFFFBF7)
val Teal = Color(0xFF2B7A78)
val Coral = Color(0xFFEA6B4A)
val Moss = Color(0xFF5D7A52)
val Sky = Color(0xFFE5EEF2)
val LightPrimaryContainer = blend(Sand, Teal, 0.18f)
val LightSecondaryContainer = blend(Sand, Coral, 0.12f)
val LightTertiaryContainer = blend(Sand, Moss, 0.12f)
val LightSurface = blend(SurfaceWarm, Teal, 0.06f)
val LightSurfaceVariant = blend(Sky, Teal, 0.08f)
val LightError = blend(Coral, Color(0xFFB3261E), 0.4f)
val LightErrorContainer = blend(Sand, LightError, 0.24f)
val LightOutline = Ink.copy(alpha = 0.2f)
val LightOutlineVariant = Ink.copy(alpha = 0.12f)
val DarkBackground = darken(Ink, 0.45f)
val DarkSurface = blend(darken(Ink, 0.35f), Teal, 0.06f)
val DarkSurfaceVariant = blend(darken(Ink, 0.3f), Teal, 0.12f)
val DarkPrimary = blend(Teal, Sand, 0.35f)
val DarkPrimaryContainer = darken(Teal, 0.55f)
val DarkSecondary = blend(Coral, Sand, 0.28f)
val DarkSecondaryContainer = darken(Coral, 0.5f)
val DarkTertiary = blend(Moss, Sand, 0.3f)
val DarkTertiaryContainer = darken(Moss, 0.5f)
val DarkError = blend(LightError, Sand, 0.18f)
val DarkErrorContainer = darken(LightError, 0.45f)
val DarkOutline = Sand.copy(alpha = 0.35f)
val DarkOutlineVariant = Sand.copy(alpha = 0.2f)
