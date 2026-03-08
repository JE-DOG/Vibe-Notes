# Spring Learning Track Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Build a lesson-based Spring Boot + Kotlin practice project where the student implements features and verifies
correctness through tests.

**Architecture:** Keep legacy app code untouched where possible and add a dedicated `learning` package for educational
tasks. Each lesson has its own Markdown brief and matching test suite, with production stubs intentionally left
incomplete (`TODO`) to enforce TDD from the student side.

**Tech Stack:** Kotlin 1.9, Spring Boot 3.5, Spring Security, Exposed DSL, JUnit 5, MockMvc-ready test starter, Docker
Compose.

---

### Task 1: Stabilize compilation baseline

**Files:**

- Modify: `src/main/kotlin/ru/khinkal/springDemo/common/auth/JwtFilter.kt`

**Step 1: Write/identify failing signal**

- Run: `./gradlew compileKotlin`
- Expected: compilation error around `doFilterInternal` override signature.

**Step 2: Minimal fix**

- Change `doFilterInternal` signature to match `OncePerRequestFilter` contract (`HttpServletResponse` non-null).

**Step 3: Verify baseline**

- Run: `./gradlew compileKotlin`
- Expected: `BUILD SUCCESSFUL`.

### Task 2: Add lesson documentation structure

**Files:**

- Create: `lessons/README.md`
- Create: `lessons/lesson-00-roadmap/README.md`
- Create: `lessons/lesson-01-exposed/README.md`
- Create: `lessons/lesson-02-jwt/README.md`
- Create: `lessons/lesson-03-auth-service/README.md`
- Create: `lessons/lesson-04-docker-compose/README.md`

**Step 1: Add track overview**

- Explain sequence, Android-to-Spring mental mapping, and lesson run commands.

**Step 2: Add per-lesson briefs**

- Include goals, practical tasks, checkpoints, links to official resources, and concise takeaways.

**Step 3: Verify docs quality**

- Check every lesson has explicit acceptance criteria tied to tests.

### Task 3: Create educational production stubs

**Files:**

- Create/Modify under: `src/main/kotlin/ru/khinkal/springDemo/learning/**`

**Step 1: Lesson 1 stubs (Exposed)**

- Define user model, table, repository contract, and TODO implementation.

**Step 2: Lesson 2 stubs (JWT)**

- Define principal model, token service contract, TODO implementation, and domain exceptions.

**Step 3: Lesson 3 stubs (Auth use case)**

- Define command/response models, hasher contract, TODO service and exceptions.

### Task 4: Write red tests per lesson

**Files:**

- Create: `src/test/kotlin/ru/khinkal/springDemo/learning/lesson01exposed/LessonUsersRepositoryTest.kt`
- Create: `src/test/kotlin/ru/khinkal/springDemo/learning/lesson02jwt/JwtTokenServiceTest.kt`
- Create: `src/test/kotlin/ru/khinkal/springDemo/learning/lesson03auth/AuthServiceTest.kt`
- Create: `src/test/kotlin/ru/khinkal/springDemo/learning/lesson04docker/LessonDockerComposeContractTest.kt`
- Create: `lessons/lesson-04-docker-compose/docker-compose.lesson.yml`

**Step 1: RED tests for Exposed**

- Cover save/find/update contract behavior.

**Step 2: RED tests for JWT**

- Cover token generation, validation, and invalid-signature handling.

**Step 3: RED tests for Auth use case**

- Cover register/login/authenticate plus failure scenarios.

**Step 4: RED tests for Docker Compose contract**

- Validate required services and health/dependency semantics from lesson compose file.

### Task 5: Verify and communicate state

**Step 1: Build and targeted test runs**

- Run compile + selected tests.

**Step 2: Explicitly report status**

- Confirm compilation status.
- Confirm lesson tests are intentionally failing until student implementation is added.
