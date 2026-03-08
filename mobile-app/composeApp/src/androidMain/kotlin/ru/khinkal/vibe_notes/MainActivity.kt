package ru.khinkal.vibe_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.khinkal.vibe_notes.di.PlatformContext
import ru.khinkal.vibe_notes.ui.theme.LocalActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalActivity provides this) {
                App(platformContext = PlatformContext(applicationContext))
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    CompositionLocalProvider(LocalActivity provides null) {
        App(platformContext = PlatformContext(LocalContext.current))
    }
}
