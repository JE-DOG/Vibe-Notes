package ru.khinkal.vibe_notes.data.network

import ru.khinkal.vibe_notes.data.model.User

class UserApi(
    private val apiClient: ApiClient,
) {
    suspend fun me(): ApiResult<User> = apiClient.get("/api/users/me")
}
