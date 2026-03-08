package ru.khinkal.springDemo.learning.lesson02jwt

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.time.Duration.Companion.hours

class JwtTokenServiceTest {

    private val service = JwtTokenServiceImpl(
        secret = "lesson-secret",
        issuer = "spring-demo-learning",
        accessTokenTtl = 1.hours,
    )

    @Test
    fun `generateAccessToken and parseAndValidate perform roundtrip`() {
        val userId = UUID.randomUUID()

        val token = service.generateAccessToken(
            userId = userId,
            email = "neo@example.com",
            roles = setOf("USER", "AUTHOR"),
        )
        val principal = service.parseAndValidate(token)

        assertEquals(userId, principal.userId)
        assertEquals("neo@example.com", principal.email)
        assertEquals(setOf("USER", "AUTHOR"), principal.roles)
    }

    @Test
    fun `parseAndValidate throws InvalidTokenException for token signed with another secret`() {
        val foreignService = JwtTokenServiceImpl(
            secret = "foreign-secret",
            issuer = "spring-demo-learning",
            accessTokenTtl = 1.hours,
        )
        val token = foreignService.generateAccessToken(
            userId = UUID.randomUUID(),
            email = "foreign@example.com",
            roles = setOf("USER"),
        )

        assertFailsWith<InvalidTokenException> {
            service.parseAndValidate(token)
        }
    }

    @Test
    fun `parseAndValidate throws InvalidTokenException for malformed token`() {
        assertFailsWith<InvalidTokenException> {
            service.parseAndValidate("not-a-jwt")
        }
    }
}
