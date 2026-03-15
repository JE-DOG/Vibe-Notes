package ru.khinkal.vibeNotes.feature.data.article

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction
import ru.khinkal.vibeNotes.feature.data.article.db.ArticleTable
import ru.khinkal.vibeNotes.feature.domain.aritcle.ArticleRepository
import ru.khinkal.vibeNotes.feature.domain.aritcle.model.Article
import ru.khinkal.vibeNotes.feature.domain.aritcle.model.CreateArticleBody

class ArticleRepositoryImpl(
    private val articleTable: ArticleTable,
) : ArticleRepository {

    override suspend fun getUserArticles(userId: Int): List<Article> {
        return withContext(Dispatchers.IO) {
            transaction {
                articleTable.select {
                    articleTable.userId eq userId
                }
                    .map { it.toArticle() }
            }
        }
    }

    override suspend fun getArticle(articleId: Int): Article {
        return withContext(Dispatchers.IO) {
            transaction {
                articleTable.select { articleTable.id eq articleId }
                    .let { query ->
                        val queryIterator = query.iterator()
                        check(queryIterator.hasNext()) {
                            "Don't has article with id = '$articleId'"
                        }
                        val articleRow = queryIterator.next()
                        check(!queryIterator.hasNext()) {
                            "Find 2 articles with id = '$articleId'"
                        }

                        articleRow.toArticle()
                    }
            }
        }
    }

    override suspend fun createArticle(article: CreateArticleBody) {
        withContext(Dispatchers.IO) {
            transaction {
                articleTable.insert { newArticleRow ->
                    newArticleRow.setArticle(article)
                }
            }
        }
    }

    private fun ResultRow.toArticle(): Article {
        return Article(
            id = this[articleTable.id],
            userId = this[articleTable.userId],
            title = this[articleTable.title],
            content = this[articleTable.content],
        )
    }

    private fun InsertStatement<Number>.setArticle(
        article: CreateArticleBody,
    ) {
        this[articleTable.userId] = article.userId
        this[articleTable.title] = article.title
        this[articleTable.content] = article.content
    }
}
