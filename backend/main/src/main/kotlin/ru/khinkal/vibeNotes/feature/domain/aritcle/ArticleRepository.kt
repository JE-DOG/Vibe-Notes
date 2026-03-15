package ru.khinkal.vibeNotes.feature.domain.aritcle

import ru.khinkal.vibeNotes.feature.domain.aritcle.model.Article
import ru.khinkal.vibeNotes.feature.domain.aritcle.model.CreateArticleBody

interface ArticleRepository {

    suspend fun getUserArticles(
        userId: Int,
    ): List<Article>

    suspend fun getArticle(
        articleId: Int,
    ): Article

    suspend fun createArticle(article: CreateArticleBody)
}
