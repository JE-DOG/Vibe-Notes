package ru.khinkal.springDemo.feature.controller.article

import kotlinx.coroutines.CoroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import ru.khinkal.springDemo.feature.controller.article.model.CreateArticleRequestBody
import ru.khinkal.springDemo.feature.domain.aritcle.ArticleRepository
import ru.khinkal.springDemo.feature.domain.aritcle.model.Article
import ru.khinkal.springDemo.feature.domain.aritcle.model.CreateArticleBody

@RestController
class ArticleController(
    private val articleRepository: ArticleRepository,
    private val coroutineScope: CoroutineScope,
) {

    @GetMapping("/articles")
    suspend fun getArticles(
        @AuthenticationPrincipal userId: Int,
    ): ResponseEntity<List<Article>> {
        return runCatching {
            println("Getting articles1: $userId")
            val articles = articleRepository.getUserArticles(userId)

            println("Getting articles2 : $articles")

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

