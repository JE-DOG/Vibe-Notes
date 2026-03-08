package ru.khinkal.springDemo.learning.lesson01exposed

import java.util.*

class LessonUsersRepositoryImpl(
    private val usersTable: LessonUsersTable = LessonUsersTable,
) : LessonUsersRepository {

    override fun save(email: String, passwordHash: String): LessonUser {
        TODO("Lesson 01: implement insert into lesson_users")
    }

    override fun findByEmail(email: String): LessonUser? {
        TODO("Lesson 01: implement select by email")
    }

    override fun findById(id: UUID): LessonUser? {
        TODO("Lesson 01: implement select by id")
    }

    override fun updatePasswordHash(id: UUID, newPasswordHash: String): Boolean {
        TODO("Lesson 01: implement password hash update")
    }
}
