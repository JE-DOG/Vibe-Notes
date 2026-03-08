package ru.khinkal.vibeNotes.feature.data.auth

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.khinkal.vibeNotes.common.auth.JwtUtil
import ru.khinkal.vibeNotes.common.hash.HashUtil
import ru.khinkal.vibeNotes.feature.data.auth.db.AuthTable
import ru.khinkal.vibeNotes.feature.domain.auth.AuthManager
import ru.khinkal.vibeNotes.feature.domain.auth.model.SignInResult
import ru.khinkal.vibeNotes.feature.domain.auth.model.SignUpResult

class AuthManagerImpl(
    private val authTable: AuthTable = AuthTable,
) : AuthManager {

    override suspend fun signIn(
        login: String,
        password: String,
    ): SignInResult {
        val userId = transaction {
            val userRow = authTable
                .select { authTable.login eq login }
                .let { query ->
                    val queryIterator = query.iterator()
                    check(queryIterator.hasNext()) {
                        "Dont find user by login '$login'"
                    }
                    val userRow = queryIterator.next()
                    check(!queryIterator.hasNext()) {
                        "Login '$login' has more than one user"
                    }
                    val passwordHash = HashUtil.hash(password)
                    val userPasswordHash = userRow[authTable.passwordHash]
                    check(userPasswordHash == passwordHash) {
                        "Password mismatch"
                    }

                    userRow
                }

            userRow[authTable.userId]
        }
        val userJwt = JwtUtil.generateToken(userId.toString())

        return SignInResult(
            jwt = userJwt,
        )
    }

    override suspend fun signUp(
        login: String,
        password: String,
    ): SignUpResult {
        val passwordHash = HashUtil.hash(password)
        val userId = transaction {
            val newUserRow = authTable.insert { column ->
                column[this.login] = login
                column[this.passwordHash] = passwordHash
            }

            newUserRow[authTable.userId]
        }
        val userJwt = JwtUtil.generateToken(userId.toString())

        return SignUpResult(
            jwt = userJwt,
        )
    }
}
