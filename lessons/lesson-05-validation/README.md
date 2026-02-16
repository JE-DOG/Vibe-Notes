# Lesson 05. Bean Validation в Spring

## Цель

Освоить валидацию входных моделей через `jakarta.validation` так, чтобы правила были декларативными и проверялись
одинаково в любой точке приложения.

## Где писать код

- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson05validation/RegistrationRequest.kt`
- `src/main/kotlin/ru/khinkal/springDemo/learning/lesson05validation/RegistrationValidationService.kt`

## Что реализовать

1. Добавить аннотации валидации на поля `RegistrationRequest`:

- email: не пустой и валидный email;
- password: не короче 8 символов;
- displayName: 3..32 символов.

2. Реализовать `RegistrationValidationService.validate(...)`:

- запускать Bean Validation через `Validator`;
- возвращать список `FieldViolation` (поле + сообщение) без дублей;
- сортировать ошибки по имени поля для стабильных тестов.

## Ресурсы

- [Spring Boot Validation](https://docs.spring.io/spring-boot/reference/io/validation.html)
    - официально про методную и DTO-валидацию.
- [Spring Framework Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
    - как Spring интегрирует Jakarta Validation.
- [Hibernate Validator reference](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/)
    - референс по constraint-аннотациям и сообщениям.
- [Spring MVC Validation config](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/validation.html)
    - глобальная настройка валидации в web-слое.

## Выжимка

- Валидация не должна жить в `if`-ах сервиса.
- Контракт модели задаётся аннотациями, а не комментариями.
- Стабильная сортировка ошибок сильно упрощает тесты и API-контракты.

## Проверка

- `./gradlew test --tests "ru.khinkal.springDemo.learning.lesson05validation.*"`
