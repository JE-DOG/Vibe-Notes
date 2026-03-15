package ru.khinkal.vibe_notes.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AndroidTokenStore(
    private val dataStore: DataStore<Preferences>,
) : TokenStore {
    override val tokenFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[TOKEN_KEY]
    }
    override val loginFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[LOGIN_KEY]
    }

    override suspend fun readToken(): String? = tokenFlow.first()
    override suspend fun readLogin(): String? = loginFlow.first()

    override suspend fun writeToken(token: String?) {
        dataStore.edit { prefs ->
            if (token == null) {
                prefs.remove(TOKEN_KEY)
            } else {
                prefs[TOKEN_KEY] = token
            }
        }
    }

    override suspend fun writeLogin(login: String?) {
        dataStore.edit { prefs ->
            if (login == null) {
                prefs.remove(LOGIN_KEY)
            } else {
                prefs[LOGIN_KEY] = login
            }
        }
    }

    private companion object {

        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val LOGIN_KEY = stringPreferencesKey("auth_login")
    }
}
