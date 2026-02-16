package ru.khinkal.springDemo.feature.article

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.khinkal.springDemo.feature.article.model.Article
import ru.khinkal.springDemo.feature.article.model.mocks.ArticleMocks

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController {

    private val articles = mutableListOf<Article>()
        .apply { addAll(ArticleMocks.createList(10)) }

    @GetMapping
    fun articles(): List<Article> = articles
}
