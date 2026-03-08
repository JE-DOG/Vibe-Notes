package ru.khinkal.springDemo.learning.lesson06problem

import org.springframework.http.ProblemDetail
import java.net.URI

class LessonProblemDetailsMapperImpl : LessonProblemDetailsMapper {

    override fun toProblem(
        exception: Throwable,
        instance: URI,
    ): ProblemDetail {
        TODO("Lesson 06: map domain exceptions to ProblemDetail")
    }
}
