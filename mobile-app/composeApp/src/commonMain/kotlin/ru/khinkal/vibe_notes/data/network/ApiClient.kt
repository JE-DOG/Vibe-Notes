package ru.khinkal.vibe_notes.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.isSuccess
import io.ktor.http.takeFrom
import ru.khinkal.vibe_notes.data.storage.TokenStore

class ApiClient(
    @PublishedApi
    internal val httpClient: HttpClient,
    @PublishedApi
    internal val tokenStore: TokenStore,
) {
    suspend inline fun <reified T> get(
        path: String,
        auth: Boolean = true,
    ): ApiResult<T> = request(HttpMethod.Get, path, auth, null)

    suspend inline fun <reified T, reified B> post(
        path: String,
        body: B,
        auth: Boolean = true,
    ): ApiResult<T> = request(HttpMethod.Post, path, auth, body)

    suspend inline fun <reified T, reified B> put(
        path: String,
        body: B,
        auth: Boolean = true,
    ): ApiResult<T> = request(HttpMethod.Put, path, auth, body)

    suspend inline fun <reified T> delete(
        path: String,
        auth: Boolean = true,
    ): ApiResult<T> = request(HttpMethod.Delete, path, auth, null)

    suspend inline fun <reified T> request(
        method: HttpMethod,
        path: String,
        auth: Boolean,
        body: Any?,
    ): ApiResult<T> {
        return try {
            val token = if (auth) tokenStore.readToken() else null
            val response = httpClient.request {
                this.method = method
                url {
                    takeFrom(ApiConfig.BASE_URL)
                    encodedPath = path
                }
                contentType(ContentType.Application.Json)
                if (token != null) {
                    headers.append("Authorization", "Bearer $token")
                }
                if (body != null) {
                    setBody(body)
                }
            }
            if (response.status.isSuccess()) {
                if (T::class == Unit::class) {
                    @Suppress("UNCHECKED_CAST")
                    return ApiResult.Success(Unit as T)
                }
                ApiResult.Success(response.body())
            } else {
                if (response.status == HttpStatusCode.Unauthorized) {
                    tokenStore.clearToken()
                }
                ApiResult.Error(
                    statusCode = response.status.value,
                    message = response.errorMessage(),
                )
            }
        } catch (throwable: Throwable) {
            ApiResult.Error(
                message = throwable.message ?: "Unexpected error",
                cause = throwable,
            )
        }
    }
}

@PublishedApi
internal suspend fun io.ktor.client.statement.HttpResponse.errorMessage(): String {
    val fallback = "HTTP ${status.value}"
    return runCatching {
        body<ErrorResponse>().let { error ->
            error.message ?: error.error ?: fallback
        }
    }.getOrDefault(fallback)
}
