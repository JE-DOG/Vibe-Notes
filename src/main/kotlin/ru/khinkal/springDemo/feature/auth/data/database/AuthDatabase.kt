package ru.khinkal.springDemo.feature.auth.data.database

import org.jetbrains.exposed.sql.Table

object AuthDatabase : Table("auth_table") {

    val id = uuid("id")
    override val primaryKey: PrimaryKey = PrimaryKey(
        name = "id",
        columns = arrayOf(id),
    )

    val login = varchar("login", 50)
    val passwordHash = varchar("passwordHash", 255)
}
