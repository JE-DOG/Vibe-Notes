package ru.khinkal.springDemo.learning.lesson03auth

import ru.khinkal.springDemo.learning.lesson01exposed.LessonUser
import ru.khinkal.springDemo.learning.lesson01exposed.LessonUsersRepository
import ru.khinkal.springDemo.learning.lesson02jwt.JwtTokenService

class AuthService(
    private val usersRepository: LessonUsersRepository,
    private val jwtTokenService: JwtTokenService,
    private val passwordHasher: PasswordHasher,
) {

    fun register(request: RegisterRequest): AuthSession {
        TODO("Lesson 03: implement register flow")
    }

    fun login(request: LoginRequest): AuthSession {
        TODO("Lesson 03: implement login flow")
    }

    fun authenticate(accessToken: String): LessonUser {
        TODO("Lesson 03: implement token-based user lookup")
    }
}
