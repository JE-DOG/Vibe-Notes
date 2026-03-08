package ru.khinkal.springDemo.learning.lesson03auth

interface PasswordHasher {

    fun hash(rawPassword: String): String

    fun matches(rawPassword: String, passwordHash: String): Boolean
}
