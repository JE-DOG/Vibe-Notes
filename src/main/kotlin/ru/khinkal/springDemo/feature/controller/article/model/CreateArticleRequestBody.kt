package ru.khinkal.springDemo.feature.controller.article.model

data class CreateArticleRequestBody(
    val title: String,
    val content: String,
)
