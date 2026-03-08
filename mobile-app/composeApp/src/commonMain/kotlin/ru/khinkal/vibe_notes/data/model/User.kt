@file:OptIn(kotlin.time.ExperimentalTime::class)

package ru.khinkal.vibe_notes.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val createdAt: Instant,
)
