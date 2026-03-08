package ru.khinkal.vibe_notes.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import ru.khinkal.vibe_notes.data.network.installCommonPlugins
import ru.khinkal.vibe_notes.data.storage.IosTokenStore
import ru.khinkal.vibe_notes.data.storage.TokenStore

@BindingContainer
actual class PlatformBindings actual constructor(
    @Suppress("UNUSED_PARAMETER")
    private val platformContext: PlatformContext,
) {
    @SingleIn(AppScope::class)
    @Provides
    fun provideTokenStore(): TokenStore = IosTokenStore()

    @SingleIn(AppScope::class)
    @Provides
    fun provideHttpClient(): HttpClient = HttpClient(Darwin) {
        expectSuccess = false
        installCommonPlugins()
    }
}
