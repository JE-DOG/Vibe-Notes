# Lesson 06. ProblemDetail и централизованная обработка ошибок

## Цель

Построить единый маппинг ошибок в HTTP-ответ (`ProblemDetail`), чтобы клиент всегда получал предсказуемый формат ошибок.

## Где писать код

- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson06problem/LessonProblemDetailsMapperImpl.kt`

## Что реализовать

1. Реализовать `LessonProblemDetailsMapperImpl.toProblem(...)`:

- `LessonEntityNotFoundException` -> `404`;
- `LessonConflictException` -> `409`;
- `LessonForbiddenException` -> `403`;
- любой другой exception -> `500`.

2. Заполнять `ProblemDetail`:

- `title`, `detail`, `instance`;
- extension property `code` (строка из enum `LessonErrorCode`).

3. Не раскрывать внутренние детали для unexpected ошибок.

## Ресурсы

- [Spring MVC Exception Handling](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)
    - базовый механизм глобальной обработки исключений.
- [Spring Error Responses / ProblemDetail](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html)
    - официально про `ProblemDetail` в Spring 6+.
- [RFC 9457 (Problem Details for HTTP APIs)](https://www.rfc-editor.org/rfc/rfc9457)
    - стандарт формата problem+json.
- [Spring Boot Error Handling](https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.error-handling)
    - как это работает в Boot-приложении.

## Выжимка

- Единый error-format уменьшает хаос в клиенте.
- Доменные исключения должны маппиться в явные HTTP статусы.
- Неожиданные ошибки должны быть безопасными для клиента и информативными для логов.

## Проверка

- `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson06problem.*"`
