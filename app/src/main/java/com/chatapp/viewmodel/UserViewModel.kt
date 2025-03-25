package com.chatapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.data.remote.request.LoginRequest
import com.chatapp.data.remote.request.UserRequest
import com.chatapp.model.UiState
import com.chatapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState = mutableStateOf<UiState<Boolean>>(UiState.Success(false))
    val loginState: State<UiState<Boolean>> = _loginState
    val token: String=""


    fun login(userId: String, password: String) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                val response = userRepository.login(userId, password)
                if (response.isSuccessful) {
                    // 登陆成功，保存token


                    _loginState.value = UiState.Success(true)

                } else {
                    _loginState.value = UiState.Error("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private val _registerState = mutableStateOf<UiState<Boolean>>(UiState.Success(false))
    val registerState: State<UiState<Boolean>> = _registerState

    fun register(nickname: String, password: String) {
        viewModelScope.launch {
            _registerState.value = UiState.Loading
            try {
                val response = userRepository.register(UserRequest(nickname, password))
                if (response.isSuccessful) {
                    _registerState.value = UiState.Success(true)
                } else {
                    _registerState.value = UiState.Error("Registration failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _registerState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
