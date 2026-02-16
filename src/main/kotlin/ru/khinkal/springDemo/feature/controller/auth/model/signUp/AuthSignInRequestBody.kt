package ru.khinkal.springDemo.feature.controller.auth.model.signUp

data class AuthSignInRequestBody(
    val login: String,
    val password: String,
)