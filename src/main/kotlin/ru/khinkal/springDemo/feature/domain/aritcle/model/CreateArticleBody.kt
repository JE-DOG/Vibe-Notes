package ru.khinkal.springDemo.feature.domain.aritcle.model

data class CreateArticleBody(
    val userId: Int,
    val title: String,
    val content: String,
)
