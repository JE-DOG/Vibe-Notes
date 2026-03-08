package ru.khinkal.vibeNotes.feature.controller.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.khinkal.vibeNotes.feature.controller.auth.model.signIn.AuthSignInResponseBody
import ru.khinkal.vibeNotes.feature.controller.auth.model.signIn.AuthSignUpRequestBody
import ru.khinkal.vibeNotes.feature.controller.auth.model.signUp.AuthSignInRequestBody
import ru.khinkal.vibeNotes.feature.controller.auth.model.signUp.AuthSignUpResponseBody
import ru.khinkal.vibeNotes.feature.domain.auth.AuthManager

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authManager: AuthManager,
    private val coroutineScope: CoroutineScope,
) {

    @PostMapping("/sign-up")
    suspend fun signUp(
        @RequestBody request: AuthSignUpRequestBody,
    ): ResponseEntity<AuthSignUpResponseBody?> {
        val signUpResultDeferred = coroutineScope.async {
            authManager.signUp(
                login = request.login,
                password = request.password,
            )
        }

        return runCatching {
            val signUpResult = signUpResultDeferred.await()
            val responseBody = AuthSignUpResponseBody(
                jwt = signUpResult.jwt,
            )

            ResponseEntity.ok(responseBody)
        }
            .getOrElse { throwable ->
                throwable.printStackTrace()

                ResponseEntity
                    .internalServerError()
                    .body(null)
            }
    }

    @PostMapping("/sign-in")
    suspend fun signIn(
        @RequestBody request: AuthSignInRequestBody,
    ): ResponseEntity<AuthSignInResponseBody> {
        val signInResultDeferred = coroutineScope.async {
            authManager.signIn(
                login = request.login,
                password = request.password,
            )
        }

        return runCatching {
            val signInResult = signInResultDeferred.await()
            val responseBody = AuthSignInResponseBody(
                jwt = signInResult.jwt,
            )

            ResponseEntity.ok(responseBody)
        }
            .getOrElse { throwable ->
                throwable.printStackTrace()

                ResponseEntity
                    .internalServerError()
                    .body(null)
            }
    }
}
