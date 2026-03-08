package ru.khinkal.springDemo.learning.lesson06problem

sealed class LessonDomainException(
    val code: LessonErrorCode,
    message: String,
) : RuntimeException(message)

class LessonEntityNotFoundException(entity: String, id: String) : LessonDomainException(
    code = LessonErrorCode.NOT_FOUND,
    message = "$entity with id=$id not found",
)

class LessonConflictException(message: String) : LessonDomainException(
    code = LessonErrorCode.CONFLICT,
    message = message,
)

class LessonForbiddenException(message: String) : LessonDomainException(
    code = LessonErrorCode.FORBIDDEN,
    message = message,
)
