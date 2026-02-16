package ru.khinkal.springDemo.learning.lesson06problem

import org.springframework.http.ProblemDetail
import java.net.URI

interface LessonProblemDetailsMapper {

    fun toProblem(
        exception: Throwable,
        instance: URI,
    ): ProblemDetail
}
