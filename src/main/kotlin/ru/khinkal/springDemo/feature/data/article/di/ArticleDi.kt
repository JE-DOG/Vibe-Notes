package ru.khinkal.springDemo.feature.data.article.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.khinkal.springDemo.feature.data.article.ArticleRepositoryImpl
import ru.khinkal.springDemo.feature.data.article.db.ArticleTable
import ru.khinkal.springDemo.feature.domain.aritcle.ArticleRepository

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
