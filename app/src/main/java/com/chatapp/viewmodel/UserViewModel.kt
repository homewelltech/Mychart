package com.chatapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // 用于表示“登录是否成功”的状态：成功、错误、加载中
    private val _loginState = mutableStateOf<UiState<Boolean>>(UiState.Success(false))
    val loginState: State<UiState<Boolean>> = _loginState

    // 这里存储从后端获取的 token
    private val _token = mutableStateOf("")
    val token: State<String> = _token

    /**
     * 登录函数：从后端拿到 token 并存入 _token，更新登录状态
     */
    fun login(userId: String, password: String) {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                val response = userRepository.login(userId, password)
                if (response.isSuccessful) {
                    // 登录成功
                    val newToken = response.body()?.data?.token ?: ""
                    // 存入 _token state，以供界面或后续逻辑使用
                    _token.value = newToken

                    // 更新登录状态为成功
                    _loginState.value = UiState.Success(true)

                } else {
                    // 登录失败
                    _loginState.value = UiState.Error(
                        "Login failed: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // 网络或其他异常
                _loginState.value = UiState.Error(
                    e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }

    // 注册逻辑，与之前相同
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
                    _registerState.value = UiState.Error(
                        "Registration failed: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _registerState.value = UiState.Error(
                    e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }
}
