package edu.metrostate.ics342.mediatracker.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel /*(
    private val userRepository: UserRepository
) */: ViewModel()    {

    private val _displayName = MutableStateFlow("")
    val displayName = _displayName.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun setDisplayName(newValue: String) {
        _displayName.value = newValue
    }

    fun setUserName(newValue: String) {
        _userName.value = newValue
    }

    fun setEmail(newValue: String) {
        _email.value = newValue
    }

    fun setPassword(newValue: String) {
        _password.value = newValue
    }

    fun setConfirmPassword(newValue: String) {
        _confirmPassword.value = newValue
    }


    fun onSignUpClicked() {
        if (!isFormValid()) {
            return
        }
        Log.e("RegisterScreen", "Your form data checked out, but, sadly, the backend is not implemented.!", )
        _errorMessage.value = "Your form data checked out, but, sadly, the backend is not implemented.!"
        viewModelScope.launch {
            //userRepository.createAccount(displayName.value, userName.value, email.value, password.value)
        }
    }

    fun isFormValid(): Boolean {
        var isValid: Boolean = true
        if (confirmPassword.value != password.value) {
            Log.e("RegisterScreen", "ValidationError: passwords don't match! ", )
            _errorMessage.value = "Passwords do not match!"
            isValid = false
        } else if (displayName.value.isBlank() || userName.value.isBlank() || email.value.isBlank() ||
            password.value.isBlank() || confirmPassword.value.isBlank()) {
            Log.e("RegisterScreen","ValidationError: One or more fields is null or empty")
            _errorMessage.value = "You must fill out all fields!"
            isValid = false
        }
        return isValid
    }
}