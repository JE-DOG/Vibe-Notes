package ru.khinkal.vibe_notes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.network.AuthApi
import ru.khinkal.vibe_notes.data.storage.TokenStore

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
) {
    val tokenFlow: Flow<String?> = tokenStore.tokenFlow
    val loginFlow: Flow<String?> = tokenStore.loginFlow

    val isSignedIn: Flow<Boolean> = tokenFlow.map { !it.isNullOrBlank() }

    suspend fun register(login: String, password: String): ApiResult<Unit> =
        when (val result = authApi.register(login, password)) {
            is ApiResult.Success -> {
                tokenStore.writeToken(result.value.jwt)
                tokenStore.writeLogin(login)
                ApiResult.Success(Unit)
            }

            is ApiResult.Error -> result
        }

    suspend fun login(login: String, password: String): ApiResult<Unit> =
        when (val result = authApi.login(login, password)) {
            is ApiResult.Success -> {
                tokenStore.writeToken(result.value.jwt)
                tokenStore.writeLogin(login)
                ApiResult.Success(Unit)
            }

            is ApiResult.Error -> result
        }

    suspend fun logout() {
        tokenStore.clearToken()
        tokenStore.clearLogin()
    }
}
