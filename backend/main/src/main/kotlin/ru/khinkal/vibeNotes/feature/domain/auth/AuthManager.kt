package ru.khinkal.vibeNotes.feature.domain.auth

import ru.khinkal.vibeNotes.feature.domain.auth.model.SignInResult
import ru.khinkal.vibeNotes.feature.domain.auth.model.SignUpResult

interface AuthManager {

    suspend fun signIn(
        login: String,
        password: String,
    ): SignInResult

    suspend fun signUp(
        login: String,
        password: String,
    ): SignUpResult
}
