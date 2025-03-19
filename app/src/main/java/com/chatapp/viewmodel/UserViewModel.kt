package com.chatapp.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.api.AuthResponse
import com.chatapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = userRepository.login(username, password)
                if (response.isSuccessful) {
                    response.body()?.let { authResponse ->
                        saveUserData(username, password, authResponse.token, authResponse.refreshToken)
                        onSuccess()
                    } ?: onError("登录失败，服务器返回数据为空")
                } else {
                    onError("登录失败: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("网络错误: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUserData(username: String, password: String, token: String, refreshToken: String) {
        sharedPreferences.edit().apply {
            putString("USERNAME", username)
            putString("PASSWORD", password)
            putString("TOKEN", token)
            putString("REFRESH_TOKEN", refreshToken)
            apply()
        }
    }

    fun getSavedUserData(): Pair<String, String>? {
        val username = sharedPreferences.getString("USERNAME", null)
        val password = sharedPreferences.getString("PASSWORD", null)
        return if (username != null && password != null) Pair(username, password) else null
    }

    fun getAuthToken(): String? = sharedPreferences.getString("TOKEN", null)
}

