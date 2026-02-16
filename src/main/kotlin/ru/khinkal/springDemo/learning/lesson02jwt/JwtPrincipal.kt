package ru.khinkal.springDemo.learning.lesson02jwt

import java.util.*

data class JwtPrincipal(
    val userId: UUID,
    val email: String,
    val roles: Set<String>,
)
