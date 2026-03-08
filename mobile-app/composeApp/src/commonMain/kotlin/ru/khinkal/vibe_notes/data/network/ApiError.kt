package ru.khinkal.vibe_notes.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String? = null,
    val error: String? = null,
)
