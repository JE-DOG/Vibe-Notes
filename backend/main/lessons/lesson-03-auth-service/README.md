# Lesson 03. Auth Use Case: register/login/me

## Цель

Собрать сервис авторизации как use-case слой: без контроллера, но с реальными бизнес-правилами.

## Где писать код

- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson03auth/AuthService.kt`

## Что реализовать

1. `register(request)`

- проверяет, что email свободен;
- хэширует пароль;
- сохраняет пользователя;
- выдает access token.

2. `login(request)`

- проверяет наличие пользователя;
- проверяет пароль;
- выдает access token.

3. `authenticate(accessToken)`

- валидирует токен через `JwtTokenService`;
- достаёт пользователя из репозитория.

## Ресурсы

- [Spring Security architecture](https://docs.spring.io/spring-security/reference/servlet/architecture.html)
    - Очень полезно, чтобы понимать границы аутентификации и авторизации.
- [Spring Boot testing guide](https://docs.spring.io/spring-boot/how-to/testing.html)
    - Покрывает паттерны unit/integration тестов в Spring-проектах.
- [OWASP Password Storage Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html)
    - Практический минимум по хранению паролей.

## Выжимка

- `AuthService` должен быть доменным уровнем, а не HTTP-слоем.
- Сервис не должен знать ничего про контроллеры/response DTO.
- Не смешивай ответственность: проверка пароля, доступ к БД и JWT должны быть декомпозированы через контракты.

## Проверка

- Запуск: `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson03auth.*"`
- Тесты проверяют happy-path и ошибки (`duplicate email`, `wrong password`, `invalid token`).
