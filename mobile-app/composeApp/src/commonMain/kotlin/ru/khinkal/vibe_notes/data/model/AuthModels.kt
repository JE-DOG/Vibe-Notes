package ru.khinkal.vibe_notes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val login: String,
    val password: String,
)

@Serializable
data class AuthResponse(
    val jwt: String,
)
