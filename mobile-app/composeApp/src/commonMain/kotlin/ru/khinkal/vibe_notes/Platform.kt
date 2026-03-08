package ru.khinkal.vibe_notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform