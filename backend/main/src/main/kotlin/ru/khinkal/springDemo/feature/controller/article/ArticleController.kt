package ru.khinkal.springDemo.feature.controller.article

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.khinkal.springDemo.feature.controller.article.model.CreateArticleRequestBody
import ru.khinkal.springDemo.feature.domain.aritcle.ArticleRepository
import ru.khinkal.springDemo.feature.domain.aritcle.model.Article
import ru.khinkal.springDemo.feature.domain.aritcle.model.CreateArticleBody

@RestController
class ArticleController(
    private val articleRepository: ArticleRepository,
) {

    @GetMapping("/articles")
    suspend fun getArticles(
        @AuthenticationPrincipal userId: Int,
    ): ResponseEntity<List<Article>> {
        return runCatching {
            val articles = articleRepository.getUserArticles(userId)
            ResponseEntity.ok(articles)
        }
            .getOrElse { throwable ->
                throwable.printStackTrace()

                ResponseEntity
                    .internalServerError()
                    .body(null)
            }
    }

    @GetMapping("/article/{articleId}")
    suspend fun getArticle(
        @PathVariable articleId: Int,
    ): ResponseEntity<Article> {
        return runCatching {
            val article = articleRepository.getArticle(articleId)

            ResponseEntity.ok(article)
        }
            .getOrElse { throwable ->
                throwable.printStackTrace()

                ResponseEntity
                    .internalServerError()
                    .body(null)
            }
    }

    @PutMapping("/article")
    suspend fun createArticle(
        @AuthenticationPrincipal userId: Int,
        @RequestBody body: CreateArticleRequestBody,
    ): ResponseEntity<Unit?> {
        return runCatching {
            val createArticleBody = CreateArticleBody(
                userId = userId,
                title = body.title,
                content = body.content,
            )
            articleRepository.createArticle(createArticleBody)

            ResponseEntity.ok(Unit)
        }
            .getOrElse { throwable ->
                throwable.printStackTrace()

                ResponseEntity
                    .internalServerError()
                    .body(null)
            }
    }
}

