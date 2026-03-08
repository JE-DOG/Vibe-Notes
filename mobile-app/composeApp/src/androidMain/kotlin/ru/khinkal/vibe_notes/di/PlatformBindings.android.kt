package ru.khinkal.vibe_notes.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.khinkal.vibe_notes.data.network.installCommonPlugins
import ru.khinkal.vibe_notes.data.storage.AndroidTokenStore
import ru.khinkal.vibe_notes.data.storage.TokenStore

@BindingContainer
actual class PlatformBindings actual constructor(
    private val platformContext: PlatformContext,
) {
    @SingleIn(AppScope::class)
    @Provides
    fun provideDataStore(): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { platformContext.context.preferencesDataStoreFile("vibenotes.preferences_pb") },
        )

    @SingleIn(AppScope::class)
    @Provides
    fun provideTokenStore(
        dataStore: DataStore<Preferences>,
    ): TokenStore = AndroidTokenStore(dataStore)

    @SingleIn(AppScope::class)
    @Provides
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {
        expectSuccess = false
        installCommonPlugins()
    }
}
