package ru.khinkal.springDemo.learning.lesson01exposed

import org.jetbrains.exposed.sql.Table

object LessonUsersTable : Table("lesson_users") {

    val id = uuid("id")
    override val primaryKey: PrimaryKey = PrimaryKey(id)

    val email = varchar("email", 320).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val createdAtMillis = long("created_at_millis")
}
