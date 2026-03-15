package ru.khinkal.vibeNotes.common.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = request.getHeader("Authorization")

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7) // Убираем префикс "Bearer "

            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body


            // Чтение различных полей из JWT
            val userId = claims.subject.toInt() // Получаем имя пользователя (sub)

            // Логика обработки, например, создание аутентификации
            val authentication =
                UsernamePasswordAuthenticationToken(
                    userId,
                    null,
                    emptyList<SimpleGrantedAuthority>()
                ) // Указываем роли здесь, если нужно

            // Устанавливаем аутентификацию в контекст безопасности
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response) // Пропускаем запрос дальше
    }

    override fun shouldNotFilterAsyncDispatch(): Boolean {
        return false
    }

    companion object {

        private const val SECRET = "your-secret-key-must-be-at-least-32-characters-long!!"
        private val key = Keys.hmacShaKeyFor(SECRET.toByteArray())
    }
}

