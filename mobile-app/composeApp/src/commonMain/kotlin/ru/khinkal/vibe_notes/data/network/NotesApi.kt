package ru.khinkal.vibe_notes.data.network

import ru.khinkal.vibe_notes.data.model.Note
import ru.khinkal.vibe_notes.data.model.NoteRequest

class NotesApi(
    private val apiClient: ApiClient,
) {
    suspend fun getNotes(): ApiResult<List<Note>> = apiClient.get("/articles")

    suspend fun create(title: String, content: String): ApiResult<Unit> =
        apiClient.put(
            path = "/article",
            body = NoteRequest(title = title, content = content),
        )
}
