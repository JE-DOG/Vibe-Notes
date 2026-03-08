package ru.khinkal.vibe_notes.data.repository

import ru.khinkal.vibe_notes.data.model.User
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.network.UserApi

class UserRepository(
    private val userApi: UserApi,
) {
    suspend fun me(): ApiResult<User> = userApi.me()
}
