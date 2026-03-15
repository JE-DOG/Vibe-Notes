package ru.khinkal.vibeNotes.feature.data.article.db

import org.jetbrains.exposed.sql.Table
import ru.khinkal.vibeNotes.feature.data.auth.db.AuthTable

const val ARTICLE_TABLE_NAME = "articles"

object ArticleTable : Table(ARTICLE_TABLE_NAME) {

    val id = integer("id")
        .autoIncrement()
        .uniqueIndex()

    val userId = reference("user_id", AuthTable.userId)

    val title = varchar("title", 255)

    val content = text("content")

    override val primaryKey: PrimaryKey = PrimaryKey(
        columns = arrayOf(
            id,
        ),
    )
}
