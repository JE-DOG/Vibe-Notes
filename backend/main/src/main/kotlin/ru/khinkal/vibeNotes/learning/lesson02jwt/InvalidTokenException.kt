package ru.khinkal.vibeNotes.learning.lesson02jwt

class InvalidTokenException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
