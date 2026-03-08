package ru.khinkal.vibe_notes.data.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Foundation.NSUserDefaults

class IosTokenStore : TokenStore {
    private val defaults = NSUserDefaults.standardUserDefaults
    private val tokenState = MutableStateFlow(readTokenValue())

    override val tokenFlow: Flow<String?> = tokenState.asStateFlow()

    override suspend fun readToken(): String? = tokenState.value

    override suspend fun writeToken(token: String?) {
        if (token == null) {
            defaults.removeObjectForKey(TOKEN_KEY)
        } else {
            defaults.setObject(token, forKey = TOKEN_KEY)
        }
        tokenState.value = token
    }

    private fun readTokenValue(): String? = defaults.stringForKey(TOKEN_KEY)

    private companion object {
        const val TOKEN_KEY = "auth_token"
    }
}
