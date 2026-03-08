package ru.khinkal.vibe_notes.data.network

sealed interface ApiResult<out T> {
    data class Success<T>(val value: T) : ApiResult<T>

    data class Error(
        val statusCode: Int? = null,
        val message: String,
        val cause: Throwable? = null,
    ) : ApiResult<Nothing>
}
