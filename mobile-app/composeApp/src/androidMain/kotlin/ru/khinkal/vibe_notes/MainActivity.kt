package ru.khinkal.vibe_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.khinkal.vibe_notes.di.PlatformContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(platformContext = PlatformContext(applicationContext))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(platformContext = PlatformContext(LocalContext.current))
}
