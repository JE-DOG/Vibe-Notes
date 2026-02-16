package ru.khinkal.springDemo.feature.article.model

import java.time.LocalDateTime

data class Article(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val slug: String = "",
    val controller: System.Logger = System.getLogger(""),
)
