package ru.khinkal.vibe_notes.data.network

import ru.khinkal.vibe_notes.data.model.AuthRequest
import ru.khinkal.vibe_notes.data.model.AuthResponse

class AuthApi(
    private val apiClient: ApiClient,
) {
    suspend fun register(email: String, password: String): ApiResult<AuthResponse> =
        apiClient.post(
            path = "/api/auth/register",
            body = AuthRequest(email = email, password = password),
            auth = false,
        )

    suspend fun login(email: String, password: String): ApiResult<AuthResponse> =
        apiClient.post(
            path = "/api/auth/login",
            body = AuthRequest(email = email, password = password),
            auth = false,
        )
}
