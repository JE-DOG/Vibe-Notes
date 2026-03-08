package ru.khinkal.vibe_notes.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun SetStatusBarAppearanceEffect(useForLightAppearance: Boolean) {
    DisposableEffect(useForLightAppearance) {
        val targetStyle =
            if (useForLightAppearance) UIStatusBarStyleDarkContent
            else UIStatusBarStyleLightContent
        val originalStyle = UIApplication.sharedApplication.statusBarStyle

        UIApplication.sharedApplication.setStatusBarStyle(
            targetStyle,
            animated = true,
        )

        onDispose {
            UIApplication.sharedApplication.setStatusBarStyle(
                originalStyle,
                animated = true,
            )
        }
    }
}
