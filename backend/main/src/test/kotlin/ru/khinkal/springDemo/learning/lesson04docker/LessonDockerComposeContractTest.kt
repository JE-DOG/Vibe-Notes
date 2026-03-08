package ru.khinkal.springDemo.learning.lesson04docker

import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertTrue

class LessonDockerComposeContractTest {

    private val composePath: Path = Path.of(
        "lessons",
        "lesson-04-docker-compose",
        "docker-compose.lesson.yml",
    )

    @Test
    fun `docker compose lesson file exists`() {
        assertTrue(Files.exists(composePath), "Missing compose file: $composePath")
    }

    @Test
    fun `postgres service has required environment and healthcheck`() {
        val compose = Files.readString(composePath)

        assertTrue(compose.contains("postgres:"), "Service postgres is required")
        assertTrue(compose.contains("POSTGRES_DB:"), "POSTGRES_DB is required")
        assertTrue(compose.contains("POSTGRES_USER:"), "POSTGRES_USER is required")
        assertTrue(compose.contains("POSTGRES_PASSWORD:"), "POSTGRES_PASSWORD is required")
        assertTrue(compose.contains("healthcheck:"), "postgres must define healthcheck")
    }

    @Test
    fun `app service depends on healthy postgres`() {
        val compose = Files.readString(composePath)

        assertTrue(compose.contains("app:"), "Service app is required")
        assertTrue(compose.contains("depends_on:"), "app must depend on postgres")
        assertTrue(
            compose.contains("condition: service_healthy"),
            "app must wait for postgres healthcheck",
        )
    }
}
