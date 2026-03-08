package ru.khinkal.springDemo.learning.lesson02jwt

class InvalidTokenException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
