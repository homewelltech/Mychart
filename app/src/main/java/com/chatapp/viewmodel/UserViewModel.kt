package com.chatapp.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.model.Friend
import com.chatapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    /**
     * 用户登录逻辑
     */
    fun login(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.login(username, password)

                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null) {
                        saveUserData(username, password, authResponse.token, authResponse.refreshToken)
                        onSuccess() // 登录成功回调
                    } else {
                        onError("登录失败，返回数据为空")
                    }
                } else {
                    onError("登录失败，错误码: ${response.code()}")
                }

            } catch (e: Exception) {
                onError(e.message ?: "登录失败")
            }
        }
    }

    /**
     * 存储用户数据
     */
    private fun saveUserData(username: String, password: String, token: String, refreshToken: String) {
        sharedPreferences.edit {
            putString("USERNAME", username)
            putString("PASSWORD", password)
            putString("TOKEN", token)
            putString("REFRESH_TOKEN", refreshToken)
            apply()
        }
    }

    /**
     * 获取已存储的用户数据（自动填充）
     */
    fun getSavedUserData(): Pair<String, String>? {
        val username = sharedPreferences.getString("USERNAME", null)
        val password = sharedPreferences.getString("PASSWORD", null)
        return if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) Pair(username, password) else null
    }

    /**
     * 获取 JWT Token
     */
    fun getAuthToken(): String? = sharedPreferences.getString("TOKEN", null)

    /**
     * 退出登录并清除用户数据
     */
    fun logout(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.logout()
                if (response.isSuccessful) {
                    clearUserData()
                    onSuccess() // 退出成功回调
                } else {
                    onError("退出失败，错误码: ${response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "退出失败")
            }
        }
    }

    /**
     * 清除存储的用户数据
     */
    private fun clearUserData() {
        sharedPreferences.edit {
            remove("USERNAME")
            remove("PASSWORD")
            remove("TOKEN")
            remove("REFRESH_TOKEN")
            apply()
        }
    }

    /**
     * 获取好友列表
     */
    fun fetchFriendsList(onSuccess: (List<Friend>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.getFriends()
                if (response.isSuccessful) {
                    val friends = response.body() ?: emptyList()
                    onSuccess(friends)
                } else {
                    onError("获取好友列表失败，错误码: ${response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "获取好友列表失败")
            }
        }
    }

    /**
     * 添加好友
     */
    fun addFriend(username: String, friendUsername: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.addFriend(username, friendUsername)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("添加好友失败，错误码: ${response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "添加好友失败")
            }
        }
    }

    /**
     * 删除好友
     */
    fun removeFriend(friendUsername: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = userRepository.removeFriend(friendUsername)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("删除好友失败，错误码: ${response.code()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "删除好友失败")
            }
        }
    }
}