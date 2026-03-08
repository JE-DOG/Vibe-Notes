package ru.khinkal.vibe_notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.khinkal.vibe_notes.data.network.ApiResult
import ru.khinkal.vibe_notes.data.repository.AuthRepository

enum class AuthMode {
    Login,
    Register,
}

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val mode: AuthMode = AuthMode.Login,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value, errorMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value, errorMessage = null) }
    }

    fun toggleMode() {
        _state.update {
            it.copy(
                mode = if (it.mode == AuthMode.Login) AuthMode.Register else AuthMode.Login,
                errorMessage = null,
            )
        }
    }

    fun submit() {
        val email = state.value.email.trim()
        val password = state.value.password
        if (email.isBlank() || password.isBlank()) {
            _state.update { it.copy(errorMessage = "Email and password are required.") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val result = when (state.value.mode) {
                AuthMode.Login -> authRepository.login(email, password)
                AuthMode.Register -> authRepository.register(email, password)
            }
            _state.update { current ->
                when (result) {
                    is ApiResult.Success -> current.copy(isLoading = false)
                    is ApiResult.Error -> current.copy(
                        isLoading = false,
                        errorMessage = result.message,
                    )
                }
            }
        }
    }
}
