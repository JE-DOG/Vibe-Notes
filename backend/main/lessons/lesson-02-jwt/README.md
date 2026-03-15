# Lesson 02. JWT: генерация и валидация токенов

## Цель

Научиться делать безопасный и предсказуемый `JwtTokenService`, который выдает access token и валидирует его.

## Где писать код

- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson02jwt/JwtTokenServiceImpl.kt`

## Что реализовать

1. `generateAccessToken(...)`

- добавляет `sub`, `email`, `roles`, `iss`, `exp`.

2. `parseAndValidate(token)`

- валидирует подпись и срок;
- возвращает `JwtPrincipal`.

3. Ошибки

- на невалидном токене кидает `InvalidTokenException`.

## Ресурсы

- [Spring Security JWT Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
    - Официальная модель валидации JWT и важных claim-полей.
- [Spring Security testing reference](https://docs.spring.io/spring-security/reference/servlet/test/index.html)
    - Как мыслить о тестировании security поведения.
- [JWT RFC 7519](https://www.rfc-editor.org/rfc/rfc7519)
    - Первоисточник по структуре токена и стандартным claim.

## Выжимка

- JWT не хранит сессию на сервере, но хранит ответственность за корректные claim и подпись.
- `roles` и `subject` должны быть стабильным контрактом, иначе auth-слой станет хрупким.
- Все ошибки валидации токена должны приводиться к одному доменному исключению сервиса.

## Проверка

- Запуск: `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson02jwt.*"`
- Тесты проверяют валидный кейс и негативные сценарии (tampered token).
