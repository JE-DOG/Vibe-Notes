package ru.khinkal.springDemo.feature.auth.data.database.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserAuthEntity(
    @param:JsonProperty("id") val id: UUID,
    @param:JsonProperty("login") val login: String,
    @param:JsonProperty("password_hash") val passwordHash: String,
)
