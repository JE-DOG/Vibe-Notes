package ru.khinkal.springDemo.learning.lesson01exposed

import java.time.Instant
import java.util.*

data class LessonUser(
    val id: UUID,
    val email: String,
    val passwordHash: String,
    val createdAt: Instant,
)
