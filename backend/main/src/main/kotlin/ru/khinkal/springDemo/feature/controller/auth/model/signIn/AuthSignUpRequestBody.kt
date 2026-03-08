package ru.khinkal.springDemo.feature.controller.auth.model.signIn

data class AuthSignUpRequestBody(
    val login: String,
    val password: String,
)
