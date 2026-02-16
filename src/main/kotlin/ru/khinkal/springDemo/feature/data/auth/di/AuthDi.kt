package ru.khinkal.springDemo.feature.data.auth.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.khinkal.springDemo.feature.data.auth.AuthManagerImpl
import ru.khinkal.springDemo.feature.data.auth.db.AuthTable
import ru.khinkal.springDemo.feature.domain.auth.AuthManager

@Configuration
class AuthDi {

    @Bean
    fun provideAuthTable(): AuthTable = AuthTable

    @Bean
    fun provideAuthManager(
        authTable: AuthTable,
    ): AuthManager = AuthManagerImpl(authTable)
}
