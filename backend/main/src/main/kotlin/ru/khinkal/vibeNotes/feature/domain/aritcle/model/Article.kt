package ru.khinkal.vibeNotes.feature.domain.aritcle.model

data class Article(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
)
