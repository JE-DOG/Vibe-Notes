package ru.khinkal.vibe_notes.data.repository

import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.network.NotesApi

class NotesRepository(
    private val notesApi: NotesApi,
) {
    suspend fun getNotes(): ApiResult<List<Note>> = notesApi.getNotes()

    suspend fun create(title: String, content: String): ApiResult<Note> =
        notesApi.create(title, content)

    suspend fun update(id: String, title: String, content: String): ApiResult<Note> =
        notesApi.update(id, title, content)

    suspend fun delete(id: String): ApiResult<Unit> = notesApi.delete(id)
}
