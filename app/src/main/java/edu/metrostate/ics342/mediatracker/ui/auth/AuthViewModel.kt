package edu.metrostate.ics342.mediatracker.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.UserRepository
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    sealed class AuthUiState {
        object Idle    : AuthUiState()
        object Loading : AuthUiState()
        object Success : AuthUiState()
        data class Error(val msgResId: Int) : AuthUiState()
    }
    private val sessionRepository: SessionRepository = DefaultSessionRepository(application)
    // ── Login ─────────────────────────────────────────────────────────────
    private val userRepository = UserRepository()
    private val _email    = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val loginState: StateFlow<AuthUiState> = _loginState.asStateFlow()

    fun onEmailChange(value: String)    { _email.value    = value }
    fun onPasswordChange(value: String) { _password.value = value }

    fun onLoginClick() {
        if (!isValidForm()) {
            //_loginState.value = AuthUiState.Success
            return
        }
        viewModelScope.launch {
            _loginState.value = AuthUiState.Loading
            val tokenResponse = userRepository.login(_email.value, _password.value)
            if (tokenResponse.user != null) {
                Log.d("AuthViewModel","Login was a success")
                Log.d("AuthViewModel","accesstoken: ${tokenResponse.accessToken} | refreshToken: ${tokenResponse.refreshToken}")
                Log.d("AuthViewModel","user: ${tokenResponse.user}")
                sessionRepository.saveSession(
                    accessToken = tokenResponse.accessToken,
                    refreshToken = tokenResponse.refreshToken,
                    user = tokenResponse.user
                )
                _loginState.value = AuthUiState.Success
            } else {
                _loginState.value = AuthUiState.Error(edu.metrostate.ics342.mediatracker.R.string.error_problem_with_login)
            }
        }
    }

    fun resetLoginState() { _loginState.value = AuthUiState.Idle }

    fun isValidForm(): Boolean {
        if (_email.value.isBlank()) {
            AuthUiState.Error(edu.metrostate.ics342.mediatracker.R.string.error_empty_email)
            return false
        }
        if (_password.value.isBlank()) {
            AuthUiState.Error(edu.metrostate.ics342.mediatracker.R.string.error_empty_password)
            return false
        }
        return true
    }


}
