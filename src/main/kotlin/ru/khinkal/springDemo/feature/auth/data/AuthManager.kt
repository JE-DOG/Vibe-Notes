package ru.khinkal.springDemo.feature.auth.data

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.khinkal.springDemo.feature.auth.data.database.AuthDatabase
import ru.khinkal.springDemo.feature.auth.data.database.model.UserAuthEntity

class AuthManager(
    private val authDatabase: AuthDatabase = AuthDatabase,
) {

    fun insert(userAuth: UserAuthEntity) {
        transaction {
            authDatabase.insert {
                it[id] = userAuth.id
                it[login] = userAuth.login
                it[passwordHash] = userAuth.passwordHash
            }
        }
    }

    fun getAuths(): List<UserAuthEntity> = transaction {
        with(authDatabase) {
            selectAll()
                .map {
                    UserAuthEntity(
                        id = it[id],
                        login = it[login],
                        passwordHash = it[passwordHash],
                    )
                }
        }
    }
}
