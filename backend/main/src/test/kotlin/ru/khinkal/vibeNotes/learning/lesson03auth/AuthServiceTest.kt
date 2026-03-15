package ru.khinkal.vibeNotes.learning.lesson03auth

import org.junit.jupiter.api.Test
import ru.khinkal.vibeNotes.learning.lesson01exposed.LessonUser
import ru.khinkal.vibeNotes.learning.lesson01exposed.LessonUsersRepository
import ru.khinkal.vibeNotes.learning.lesson02jwt.InvalidTokenException
import ru.khinkal.vibeNotes.learning.lesson02jwt.JwtPrincipal
import ru.khinkal.vibeNotes.learning.lesson02jwt.JwtTokenService
import java.time.Instant
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AuthServiceTest {

    private val repository = InMemoryUsersRepository()
    private val hasher = FakePasswordHasher()
    private val tokenService = FakeJwtTokenService()

    private val service = AuthService(
        usersRepository = repository,
        jwtTokenService = tokenService,
        passwordHasher = hasher,
    )

    @Test
    fun `register creates user with hashed password and returns token`() {
        val session = service.register(
            RegisterRequest(
                email = "john@example.com",
                rawPassword = "plain-password",
            )
        )

        assertTrue(session.accessToken.startsWith("token-"))
        assertEquals("john@example.com", session.user.email)

        val stored = repository.findByEmail("john@example.com")
        assertNotNull(stored)
        assertEquals("hashed:plain-password", stored.passwordHash)
    }

    @Test
    fun `register fails when email already exists`() {
        repository.save(
            email = "used@example.com",
            passwordHash = "hashed:old",
        )

        assertFailsWith<EmailAlreadyUsedException> {
            service.register(
                RegisterRequest(
                    email = "used@example.com",
                    rawPassword = "new-password",
                )
            )
        }
    }

    @Test
    fun `login fails with invalid credentials`() {
        repository.save(
            email = "kate@example.com",
            passwordHash = "hashed:secret",
        )

        assertFailsWith<InvalidCredentialsException> {
            service.login(
                LoginRequest(
                    email = "kate@example.com",
                    rawPassword = "wrong",
                )
            )
        }
    }

    @Test
    fun `authenticate returns user from token subject`() {
        val saved = repository.save(
            email = "anna@example.com",
            passwordHash = "hashed:123456",
        )
        val token = tokenService.generateAccessToken(
            userId = saved.id,
            email = saved.email,
            roles = setOf("USER"),
        )

        val authenticated = service.authenticate(token)

        assertEquals(saved.id, authenticated.id)
        assertEquals(saved.email, authenticated.email)
    }

    @Test
    fun `authenticate fails for invalid token`() {
        assertFailsWith<InvalidTokenException> {
            service.authenticate("invalid-token")
        }
    }

    private class FakePasswordHasher : PasswordHasher {

        override fun hash(rawPassword: String): String = "hashed:$rawPassword"

        override fun matches(rawPassword: String, passwordHash: String): Boolean {
            return hash(rawPassword) == passwordHash
        }
    }

    private class FakeJwtTokenService : JwtTokenService {

        override fun generateAccessToken(
            userId: UUID,
            email: String,
            roles: Set<String>,
        ): String = "token-$userId"

        override fun parseAndValidate(token: String): JwtPrincipal {
            if (!token.startsWith("token-")) {
                throw InvalidTokenException("Malformed token")
            }
            val userIdRaw = token.removePrefix("token-")
            return JwtPrincipal(
                userId = UUID.fromString(userIdRaw),
                email = "from-token@example.com",
                roles = setOf("USER"),
            )
        }
    }

    private class InMemoryUsersRepository : LessonUsersRepository {

        private val usersById = mutableMapOf<UUID, LessonUser>()

        override fun save(email: String, passwordHash: String): LessonUser {
            val user = LessonUser(
                id = UUID.randomUUID(),
                email = email,
                passwordHash = passwordHash,
                createdAt = Instant.now(),
            )
            usersById[user.id] = user
            return user
        }

        override fun findByEmail(email: String): LessonUser? {
            return usersById.values.firstOrNull { it.email == email }
        }

        override fun findById(id: UUID): LessonUser? {
            return usersById[id]
        }

        override fun updatePasswordHash(id: UUID, newPasswordHash: String): Boolean {
            val current = usersById[id] ?: return false
            usersById[id] = current.copy(passwordHash = newPasswordHash)
            return true
        }
    }
}
