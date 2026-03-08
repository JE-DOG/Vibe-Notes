package ru.khinkal.springDemo.feature.domain.auth

import ru.khinkal.springDemo.feature.domain.auth.model.SignInResult
import ru.khinkal.springDemo.feature.domain.auth.model.SignUpResult

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
