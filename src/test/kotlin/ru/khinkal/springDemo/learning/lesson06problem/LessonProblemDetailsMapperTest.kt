package ru.khinkal.springDemo.learning.lesson06problem

import org.junit.jupiter.api.Test
import java.net.URI
import kotlin.test.assertEquals

class LessonProblemDetailsMapperTest {

    private val mapper = LessonProblemDetailsMapperImpl()

    @Test
    fun `maps not found exception to 404 problem detail`() {
        val problem = mapper.toProblem(
            exception = LessonEntityNotFoundException(entity = "User", id = "42"),
            instance = URI.create("/api/v1/users/42"),
        )

        assertEquals(404, problem.status)
        assertEquals("Resource not found", problem.title)
        assertEquals("User with id=42 not found", problem.detail)
        assertEquals("NOT_FOUND", problem.properties?.get("code"))
        assertEquals("/api/v1/users/42", problem.instance.toString())
    }

    @Test
    fun `maps conflict exception to 409 problem detail`() {
        val problem = mapper.toProblem(
            exception = LessonConflictException("Email already used"),
            instance = URI.create("/api/v1/auth/register"),
        )

        assertEquals(409, problem.status)
        assertEquals("Conflict", problem.title)
        assertEquals("Email already used", problem.detail)
        assertEquals("CONFLICT", problem.properties?.get("code"))
    }

    @Test
    fun `maps forbidden exception to 403 problem detail`() {
        val problem = mapper.toProblem(
            exception = LessonForbiddenException("Access denied"),
            instance = URI.create("/api/v1/admin"),
        )

        assertEquals(403, problem.status)
        assertEquals("Forbidden", problem.title)
        assertEquals("FORBIDDEN", problem.properties?.get("code"))
    }

    @Test
    fun `maps unknown exception to 500 problem detail without leaking internal message`() {
        val problem = mapper.toProblem(
            exception = IllegalStateException("DB connection leaked"),
            instance = URI.create("/api/v1/articles"),
        )

        assertEquals(500, problem.status)
        assertEquals("Unexpected error", problem.title)
        assertEquals("Unexpected server error", problem.detail)
        assertEquals("UNEXPECTED", problem.properties?.get("code"))
    }
}
