// UserRepository.kt (数据管理层)
package com.chatapp.repository

import com.chatapp.api.UserApiService
import com.chatapp.api.AuthResponse
import com.chatapp.api.UserInfoResponse
import com.chatapp.model.Friend
import com.chatapp.model.User
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(private val api: UserApiService) {

    /**
     * 用户登录
     */
    suspend fun login(username: String, password: String): Response<AuthResponse> {
        return api.login(mapOf("username" to username, "password" to password))
    }

    /**
     * 获取用户信息
     */
    suspend fun getUserInfo(): Response<User> {
        return api.getUserInfo()
    }

    /**
     * 获取好友列表
     */
    suspend fun getFriends(): Response<List<Friend>> {
        return api.getFriends()
    }

    /**
     * 添加好友
     */
    suspend fun addFriend(username: String, friendUsername: String): Response<Unit> {
        return api.addFriend(mapOf("username" to username, "friendUsername" to friendUsername))
    }

    /**
     * 删除好友
     */
    suspend fun removeFriend(friendUsername: String): Response<Unit> {
        return api.removeFriend(friendUsername)
    }

    /**
     * 用户登出
     */
    suspend fun logout(): Response<Unit> {
        return api.logout()
    }
}
