# Lesson 00. Roadmap: Android -> Spring

## Цель

Понять ментальную модель Spring Boot, чтобы не переносить Android-решения 1:1 в бэкенд.

## Маппинг ролей

- `ViewModel/UseCase` в Android -> `Service/UseCase` слой в Spring.
- `Repository` в Android -> `Repository` + SQL DSL/ORM в Spring.
- `Retrofit API` -> `@RestController` + DTO + валидация.
- `DI (Hilt/Koin)` -> `@Bean`/constructor injection + Spring Context.

## Что важно освоить перед кодом

- Границы слоёв: Controller не должен знать SQL.
- Транзакции: писать явно, понимать scope.
- Контракты: тест описывает требуемое поведение, а не детали имплементации.

## Ресурсы

- [Spring Boot + Kotlin tutorial](https://spring.io/guides/tutorials/spring-boot-kotlin)
    - Хорош для первого прохода от пустого проекта до работающего HTTP-приложения.
    - Дает базовый ритм: controller -> service -> repository.
- [Spring Boot Kotlin support](https://docs.spring.io/spring-boot/reference/features/kotlin.html)
    - Объясняет зачем нужны `kotlin-spring`, `kotlin-reflect`, jackson kotlin module.
    - Это снимает много «магии» и случайных ошибок конфигурации.
- [Spring testing web guide](https://spring.io/guides/gs/testing-web)
    - Коротко показывает как тестировать web-слой без поднятия полного сервера.

## Практика

1. Прочитай карту уроков из `lessons/README.md`.
2. Выбери первый урок (`lesson-01-exposed`) и запусти только его тесты.
3. Двигайся последовательно: `01 -> 02 -> 03 -> 04 -> 05 -> 06`.
4. Не переходи к следующему уроку, пока текущий не в `green`.

## Критерий готовности

- Ты можешь своими словами объяснить зачем backend-слою нужны:
    - транзакции,
    - отдельные DTO/модели,
    - контрактные тесты.
