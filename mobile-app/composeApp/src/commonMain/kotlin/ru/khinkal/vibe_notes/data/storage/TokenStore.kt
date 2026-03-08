package ru.khinkal.vibe_notes.data.storage

import kotlinx.coroutines.flow.Flow

interface TokenStore {
    val tokenFlow: Flow<String?>
    val loginFlow: Flow<String?>

    suspend fun readToken(): String?
    suspend fun readLogin(): String?

    suspend fun writeToken(token: String?)
    suspend fun writeLogin(login: String?)

    suspend fun clearToken() {
        writeToken(null)
    }

    suspend fun clearLogin() {
        writeLogin(null)
    }
}
