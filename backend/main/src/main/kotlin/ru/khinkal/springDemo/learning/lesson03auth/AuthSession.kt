package ru.khinkal.springDemo.learning.lesson03auth

import ru.khinkal.springDemo.learning.lesson01exposed.LessonUser

data class AuthSession(
    val accessToken: String,
    val user: LessonUser,
)
