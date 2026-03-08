@file:OptIn(kotlin.time.ExperimentalTime::class)

package ru.khinkal.vibe_notes.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)

@Serializable
data class NoteRequest(
    val title: String,
    val content: String,
)
