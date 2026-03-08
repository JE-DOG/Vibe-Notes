# Lesson 01. Exposed: таблицы и CRUD

## Цель

Собрать рабочий `Repository` на Exposed DSL и понять как backend работает с таблицами на уровне SQL-модели.

## Где писать код

- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson01exposed/LessonUsersRepositoryImpl.kt`

## Что реализовать

1. `save(email, passwordHash)`

- создаёт запись в `lesson_users`;
- возвращает созданного пользователя.

2. `findByEmail(email)`

- возвращает пользователя или `null`.

3. `findById(id)`

- возвращает пользователя или `null`.

4. `updatePasswordHash(id, newPasswordHash)`

- обновляет hash;
- возвращает `true`, если запись обновлена.

## Ресурсы

- [Exposed getting started (official)](https://www.jetbrains.com/help/exposed/getting-started-with-exposed.html)
    - База: таблицы, `transaction`, DSL-запросы.
- [Exposed DAO tutorial (official)](https://www.jetbrains.com/help/exposed/get-started-with-exposed-dao.html)
    - Полезно для сравнения DSL vs DAO; в этом уроке используем DSL.
- [Exposed GitHub](https://github.com/JetBrains/Exposed)
    - Источник правды по версиям и API.

## Выжимка

- Exposed не заменяет понимание SQL, а делает его Kotlin-типобезопасным.
- Транзакция должна быть явной границей консистентности.
- Репозиторий обязан возвращать доменную модель, а не `ResultRow` наружу.

## Проверка

- Запуск: `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson01exposed.*"`
- Тесты должны проверить вставку, выборку и обновление.
