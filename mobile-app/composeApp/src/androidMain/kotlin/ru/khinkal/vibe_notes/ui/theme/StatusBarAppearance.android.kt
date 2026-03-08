package ru.khinkal.vibe_notes.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
actual fun SetStatusBarAppearanceEffect(useForLightAppearance: Boolean) {
    val activity = LocalActivity.current
    val view = LocalView.current

    DisposableEffect(useForLightAppearance) {
        val window = activity?.window ?: return@DisposableEffect onDispose {}
        val insetsController = WindowCompat.getInsetsController(window, view)
        val original = insetsController.isAppearanceLightStatusBars
        insetsController.isAppearanceLightStatusBars = useForLightAppearance

        onDispose {
            val restoreWindow = activity.window ?: return@onDispose
            val insetController = WindowCompat.getInsetsController(restoreWindow, view)

            insetController.isAppearanceLightStatusBars = original
        }
    }
}
