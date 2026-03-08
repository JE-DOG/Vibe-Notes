package ru.khinkal.vibeNotes.common.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*
import kotlin.time.Duration.Companion.days

object JwtUtil {

    private const val SECRET = "your-secret-key-must-be-at-least-32-characters-long!!"
    private val key = Keys.hmacShaKeyFor(SECRET.toByteArray())

    fun generateToken(id: String): String {
        return Jwts.builder()
            .setSubject(id)
            .setExpiration(getNewExpirationDate())
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    private fun getNewExpirationDate(): Date =
        Date(System.currentTimeMillis() + EXPIRATION_TIME)

    val EXPIRATION_TIME get() = 10.days.inWholeMilliseconds
}
