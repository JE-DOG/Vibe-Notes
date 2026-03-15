package ru.khinkal.vibeNotes.feature.domain.aritcle.model

data class CreateArticleBody(
    val userId: Int,
    val title: String,
    val content: String,
)
