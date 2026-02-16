package ru.khinkal.springDemo.feature.article.model.mocks

import ru.khinkal.springDemo.feature.article.model.Article
import java.time.LocalDateTime

object ArticleMocks {

    fun createList(size: Int) = List(size) { create() }

    fun create(
        title: String = "title",
        content: String = "content",
        createdAt: LocalDateTime = LocalDateTime.now(),
        slug: String = "slug",
    ) = Article(
        title = title,
        content = content,
        createdAt = createdAt,
        slug = slug,
    )
}
