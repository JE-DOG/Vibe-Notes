package ru.khinkal.vibeNotes.feature.controller.auth.model.signUp

data class AuthSignInRequestBody(
    val login: String,
    val password: String,
)