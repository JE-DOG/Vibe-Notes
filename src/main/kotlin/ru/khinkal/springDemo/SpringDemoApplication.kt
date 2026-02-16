package ru.khinkal.springDemo

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.khinkal.springDemo.feature.data.article.db.ArticleTable
import ru.khinkal.springDemo.feature.data.auth.db.AuthTable

@SpringBootApplication
class SpringDemoApplication

fun main(args: Array<String>) {
    initDatabase()
    runApplication<SpringDemoApplication>(*args)
}

private fun initDatabase() {
    val dbUrl = "jdbc:postgresql://localhost:5432/demo_db"
    val dbUser = "demo_dev_rw"
    val dbPass = "dev_database_passwd"

    Database.connect(
        url = dbUrl,
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPass,
    )

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(AuthTable, ArticleTable)
    }
}
