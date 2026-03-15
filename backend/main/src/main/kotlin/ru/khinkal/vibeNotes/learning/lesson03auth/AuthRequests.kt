package ru.khinkal.vibeNotes.learning.lesson03auth

data class RegisterRequest(
    val email: String,
    val rawPassword: String,
)

data class LoginRequest(
    val email: String,
    val rawPassword: String,
)
