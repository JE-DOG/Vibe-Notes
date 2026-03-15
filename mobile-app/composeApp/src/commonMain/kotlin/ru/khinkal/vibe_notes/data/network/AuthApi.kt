package ru.khinkal.vibe_notes.data.network

import ru.khinkal.vibe_notes.data.model.AuthRequest
import ru.khinkal.vibe_notes.data.model.AuthResponse

class AuthApi(
    private val apiClient: ApiClient,
) {
    suspend fun register(login: String, password: String): ApiResult<AuthResponse> =
        apiClient.post(
            path = "/auth/sign-up",
            body = AuthRequest(login = login, password = password),
            auth = false,
        )

    suspend fun login(login: String, password: String): ApiResult<AuthResponse> =
        apiClient.post(
            path = "/auth/sign-in",
            body = AuthRequest(login = login, password = password),
            auth = false,
        )
}
