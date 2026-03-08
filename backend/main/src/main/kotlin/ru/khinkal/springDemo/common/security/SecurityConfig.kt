package ru.khinkal.springDemo.common.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher
import ru.khinkal.springDemo.common.auth.JwtFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        val authRequestMatcher = PathPatternRequestMatcher.withDefaults()
            .matcher("/auth/*")

        return http
            .disableCsrf()
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(JwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { authHttpConfig ->
                authHttpConfig
                    .requestMatchers(authRequestMatcher).permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    private fun HttpSecurity.disableCsrf(): HttpSecurity =
        csrf { it.disable() }
}
