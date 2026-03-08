package ru.khinkal.vibeNotes.learning.lesson03auth

import ru.khinkal.vibeNotes.learning.lesson01exposed.LessonUser

data class AuthSession(
    val accessToken: String,
    val user: LessonUser,
)
