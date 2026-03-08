package ru.khinkal.vibeNotes.learning.lesson01exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LessonUsersRepositoryTest {

    private val repository = LessonUsersRepositoryImpl()

    @BeforeAll
    fun connectDatabase() {
        Database.connect(
            url = "jdbc:h2:mem:lesson-users;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver",
        )
    }

    @BeforeEach
    fun createSchema() {
        transaction {
            SchemaUtils.create(LessonUsersTable)
        }
    }

    @AfterEach
    fun dropSchema() {
        transaction {
            SchemaUtils.drop(LessonUsersTable)
        }
    }

    @Test
    fun `save stores user and findByEmail returns saved user`() {
        val saved = repository.save(
            email = "alex@example.com",
            passwordHash = "hash-1",
        )

        val found = repository.findByEmail("alex@example.com")

        assertNotNull(found)
        assertEquals(saved.id, found.id)
        assertEquals("alex@example.com", found.email)
        assertEquals("hash-1", found.passwordHash)
    }

    @Test
    fun `findById returns null for unknown id`() {
        val found = repository.findById(UUID.randomUUID())

        assertNull(found)
    }

    @Test
    fun `updatePasswordHash changes stored hash`() {
        val saved = repository.save(
            email = "kate@example.com",
            passwordHash = "old-hash",
        )

        val wasUpdated = repository.updatePasswordHash(
            id = saved.id,
            newPasswordHash = "new-hash",
        )
        val updated = repository.findById(saved.id)

        assertTrue(wasUpdated)
        assertNotNull(updated)
        assertEquals("new-hash", updated.passwordHash)
    }
}
