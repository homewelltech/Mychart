// UserViewModel.kt (用户管理逻辑)
package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.UserRepository
import com.chatapp.api.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun login(username: String, password: String, onSuccess: (AuthResponse) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError("Login failed: ${'$'}{response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            }
        }
    }

    fun getUserInfo(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.getUserInfo()
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess("User: ${'$'}{it.username}") }
                } else {
                    onError("Failed to get user info: ${'$'}{response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            }
        }
    }
}