package ru.khinkal.springDemo.learning.lesson05validation

data class RegistrationRequest(
    val email: String,
    val password: String,
    val displayName: String,
)
