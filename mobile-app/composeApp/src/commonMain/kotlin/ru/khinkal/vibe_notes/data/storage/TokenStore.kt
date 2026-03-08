package ru.khinkal.vibe_notes.data.storage

import kotlinx.coroutines.flow.Flow

interface TokenStore {
    val tokenFlow: Flow<String?>

    suspend fun readToken(): String?

    suspend fun writeToken(token: String?)

    suspend fun clearToken() {
        writeToken(null)
    }
}
