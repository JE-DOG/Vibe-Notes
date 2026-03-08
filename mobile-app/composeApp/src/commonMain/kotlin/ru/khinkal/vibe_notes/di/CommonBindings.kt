package ru.khinkal.vibe_notes.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.ktor.client.HttpClient
import ru.khinkal.vibe_notes.data.network.ApiClient
import ru.khinkal.vibe_notes.data.network.AuthApi
import ru.khinkal.vibe_notes.data.network.NotesApi
import ru.khinkal.vibe_notes.data.network.UserApi
import ru.khinkal.vibe_notes.data.repository.AuthRepository
import ru.khinkal.vibe_notes.data.repository.NotesRepository
import ru.khinkal.vibe_notes.data.repository.UserRepository
import ru.khinkal.vibe_notes.data.storage.TokenStore

@BindingContainer
class CommonBindings {

    @SingleIn(AppScope::class)
    @Provides
    fun provideApiClient(
        httpClient: HttpClient,
        tokenStore: TokenStore,
    ): ApiClient = ApiClient(httpClient, tokenStore)

    @SingleIn(AppScope::class)
    @Provides
    fun provideAuthApi(apiClient: ApiClient): AuthApi = AuthApi(apiClient)

    @SingleIn(AppScope::class)
    @Provides
    fun provideNotesApi(apiClient: ApiClient): NotesApi = NotesApi(apiClient)

    @SingleIn(AppScope::class)
    @Provides
    fun provideUserApi(apiClient: ApiClient): UserApi = UserApi(apiClient)

    @SingleIn(AppScope::class)
    @Provides
    fun provideAuthRepository(
        authApi: AuthApi,
        tokenStore: TokenStore,
    ): AuthRepository = AuthRepository(authApi, tokenStore)

    @SingleIn(AppScope::class)
    @Provides
    fun provideNotesRepository(
        notesApi: NotesApi,
    ): NotesRepository = NotesRepository(notesApi)

    @SingleIn(AppScope::class)
    @Provides
    fun provideUserRepository(
        userApi: UserApi,
    ): UserRepository = UserRepository(userApi)
}
