package ru.khinkal.vibeNotes.learning.lesson02jwt

import java.util.*

interface JwtTokenService {

    fun generateAccessToken(
        userId: UUID,
        email: String,
        roles: Set<String>,
    ): String

    fun parseAndValidate(token: String): JwtPrincipal
}
