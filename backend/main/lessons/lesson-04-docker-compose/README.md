# Lesson 04. Docker Compose: app + PostgreSQL

## Цель

Собрать docker-compose контракт, в котором приложение стартует только после готовности PostgreSQL.

## Где писать код

- `lessons/lesson-04-docker-compose/docker-compose.lesson.yml`

## Что реализовать

1. Сервис `postgres`

- образ `postgres:16-alpine`;
- переменные `POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD`;
- `healthcheck` с `pg_isready`.

2. Сервис `app`

- зависит от `postgres` через `condition: service_healthy`;
- получает JDBC конфиг через переменные окружения.

3. Персистентность

- volume для данных postgres.

## Ресурсы

- [Spring Boot Docker Compose support](https://docs.spring.io/spring-boot/how-to/docker-compose.html)
    - Как Spring корректно подхватывает сервисы из compose.
- [Docker Compose specification](https://docs.docker.com/reference/compose-file/)
    - Первоисточник по синтаксису полей.
- [PostgreSQL official Docker image](https://hub.docker.com/_/postgres)
    - Обязательные env-переменные и поведение контейнера.

## Выжимка

- `depends_on` без healthcheck не гарантирует готовность БД.
- Конфиг приложения должен быть воспроизводимым локально и в CI.
- Нельзя прятать критические секреты в git-истории (для обучения пока используем простые dev-значения).

## Проверка

- Запуск: `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson04docker.*"`
- Тест проверяет наличие обязательных ключей и связей в compose-файле.
