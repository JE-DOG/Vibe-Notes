package ru.khinkal.springDemo.learning.lesson05validation

import jakarta.validation.Validation
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegistrationValidationServiceTest {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    private val service = RegistrationValidationService(
        validator = validator,
    )

    @Test
    fun `validate returns violations for invalid registration request`() {
        val violations = service.validate(
            RegistrationRequest(
                email = "not-email",
                password = "123",
                displayName = "",
            )
        )

        val fields = violations.map { it.field }.toSet()

        assertEquals(setOf("email", "password", "displayName"), fields)
    }

    @Test
    fun `validate returns empty list for valid registration request`() {
        val violations = service.validate(
            RegistrationRequest(
                email = "valid@example.com",
                password = "strong-password",
                displayName = "valid_name",
            )
        )

        assertTrue(violations.isEmpty())
    }
}
