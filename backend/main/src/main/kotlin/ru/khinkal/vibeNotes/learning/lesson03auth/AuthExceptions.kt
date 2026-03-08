package ru.khinkal.vibeNotes.learning.lesson03auth

class EmailAlreadyUsedException(email: String) : RuntimeException("Email already used: $email")

class InvalidCredentialsException : RuntimeException("Invalid credentials")

class UserNotFoundException(userId: String) : RuntimeException("User not found: $userId")
