package ru.khinkal.vibe_notes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
)

@Serializable
data class NoteRequest(
    val title: String,
    val content: String,
)
