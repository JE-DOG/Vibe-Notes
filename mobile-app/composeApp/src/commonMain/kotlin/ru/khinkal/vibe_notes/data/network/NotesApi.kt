package ru.khinkal.vibe_notes.data.network

import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.model.NoteRequest

class NotesApi(
    private val apiClient: ApiClient,
) {
    suspend fun getNotes(): ApiResult<List<Note>> = apiClient.get("/api/notes")

    suspend fun create(title: String, content: String): ApiResult<Note> =
        apiClient.post(
            path = "/api/notes",
            body = NoteRequest(title = title, content = content),
        )

    suspend fun update(id: String, title: String, content: String): ApiResult<Note> =
        apiClient.put(
            path = "/api/notes/$id",
            body = NoteRequest(title = title, content = content),
        )

    suspend fun delete(id: String): ApiResult<Unit> =
        apiClient.delete(path = "/api/notes/$id")
}
