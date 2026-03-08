package ru.khinkal.vibe_notes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.khinkal.vibe_notes.data.model.User
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.network.AuthApi
import ru.khinkal.vibe_notes.data.storage.TokenStore

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
) {
    val tokenFlow: Flow<String?> = tokenStore.tokenFlow

    val isSignedIn: Flow<Boolean> = tokenFlow.map { !it.isNullOrBlank() }

    suspend fun register(email: String, password: String): ApiResult<User> =
        when (val result = authApi.register(email, password)) {
            is ApiResult.Success -> {
                tokenStore.writeToken(result.value.token)
                ApiResult.Success(result.value.user)
            }
            is ApiResult.Error -> result
        }

    suspend fun login(email: String, password: String): ApiResult<User> =
        when (val result = authApi.login(email, password)) {
            is ApiResult.Success -> {
                tokenStore.writeToken(result.value.token)
                ApiResult.Success(result.value.user)
            }
            is ApiResult.Error -> result
        }

    suspend fun logout() {
        tokenStore.clearToken()
    }
}
