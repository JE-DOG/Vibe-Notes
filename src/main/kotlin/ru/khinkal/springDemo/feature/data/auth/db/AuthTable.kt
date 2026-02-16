package ru.khinkal.springDemo.feature.data.auth.db

import org.jetbrains.exposed.sql.Table

const val AUTH_TABLE_NAME = "auth_table"

object AuthTable : Table(AUTH_TABLE_NAME) {

    val userId = integer("userId")
        .autoIncrement()
        .uniqueIndex()

    val login = varchar(
        name = "login",
        length = 5_000,
    )
        .uniqueIndex()

    val passwordHash = varchar(
        name = "passwordHash",
        length = 5_000,
    )

    override val primaryKey: PrimaryKey = PrimaryKey(
        columns = arrayOf(
            userId,
            login,
        ),
    )
}
