package ru.khinkal.springDemo.learning.lesson01exposed

import java.util.*

interface LessonUsersRepository {

    fun save(email: String, passwordHash: String): LessonUser

    fun findByEmail(email: String): LessonUser?

    fun findById(id: UUID): LessonUser?

    fun updatePasswordHash(id: UUID, newPasswordHash: String): Boolean
}
