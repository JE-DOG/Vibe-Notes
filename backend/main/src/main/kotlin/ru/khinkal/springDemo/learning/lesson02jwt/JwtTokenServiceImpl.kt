package ru.khinkal.springDemo.learning.lesson02jwt

import java.time.Clock
import java.util.*
import kotlin.time.Duration

class JwtTokenServiceImpl(
    private val secret: String,
    private val issuer: String,
    private val accessTokenTtl: Duration,
    private val clock: Clock = Clock.systemUTC(),
) : JwtTokenService {

    override fun generateAccessToken(
        userId: UUID,
        email: String,
        roles: Set<String>,
    ): String {
        TODO("Lesson 02: generate signed JWT with required claims")
    }

    override fun parseAndValidate(token: String): JwtPrincipal {
        TODO("Lesson 02: validate JWT and map claims to JwtPrincipal")
    }
}
