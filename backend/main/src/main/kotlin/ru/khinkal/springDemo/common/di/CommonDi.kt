package ru.khinkal.springDemo.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommonDi {

    @Bean
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob())
}
