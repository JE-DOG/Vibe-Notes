package ru.khinkal.vibeNotes.feature.data.article.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.khinkal.vibeNotes.feature.data.article.ArticleRepositoryImpl
import ru.khinkal.vibeNotes.feature.data.article.db.ArticleTable
import ru.khinkal.vibeNotes.feature.domain.aritcle.ArticleRepository

@Configuration
class ArticleDi {

    @Bean
    fun provideArticleTable(): ArticleTable = ArticleTable

    @Bean
    fun provideArticleRepository(
        articleTable: ArticleTable,
    ): ArticleRepository =
        ArticleRepositoryImpl(
            articleTable = articleTable,
        )
}
