package ru.khinkal.springDemo.feature.auth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.khinkal.springDemo.feature.auth.data.AuthManager
import ru.khinkal.springDemo.feature.auth.data.database.model.UserAuthEntity

@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    private val authManager by lazy { AuthManager() }

    @GetMapping
    fun onGet(): List<UserAuthEntity> {
        return authManager.getAuths()
    }

    @PostMapping
    fun onPost(
        @RequestBody userAuth: UserAuthEntity,
    ): String {
        authManager.insert(userAuth)

        return "Success insert"
    }
}
