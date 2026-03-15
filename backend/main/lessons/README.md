# Spring Practice Track (Android -> Backend)

Этот трек построен как практикум: я дал тебе тесты и контракты, ты пишешь реализацию по TDD.

## Как работать

1. Выбираешь урок.
2. Читаешь `README.md` урока.
3. Запускаешь только тесты урока (они должны падать на старте).
4. Реализуешь код в `src/main/kotlin/ru/khinkal/springDemo/learning/...`.
5. Добиваешься зелёного результата.

## Быстрые команды

- Урок 01 (Exposed):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson01exposed.*"`
- Урок 02 (JWT):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson02jwt.*"`
- Урок 03 (Auth Service):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson03auth.*"`
- Урок 04 (Docker Compose):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson04docker.*"`
- Урок 05 (Validation):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson05validation.*"`
- Урок 06 (ProblemDetail):
    - `./gradlew test --tests "ru.khinkal.vibeNotes.learning.lesson06problem.*"`

## Карта уроков

- `lesson-00-roadmap`: как мыслить по-бэкендерски после Android.
- `lesson-01-exposed`: таблицы, транзакции, CRUD на Exposed.
- `lesson-02-jwt`: генерация и валидация JWT.
- `lesson-03-auth-service`: регистрация/логин/аутентификация как use-case слой.
- `lesson-04-docker-compose`: контракт docker-compose для app + PostgreSQL.
- `lesson-05-validation`: Bean Validation и контракт входных данных.
- `lesson-06-problem-details`: единый маппинг ошибок через ProblemDetail.

## Важно

- Тесты в этом треке специально «красные» на старте.
- Не переписывай тесты под реализацию, наоборот: реализация должна удовлетворять тестам.
- После каждого шага запускай тесты урока, а не весь проект.
