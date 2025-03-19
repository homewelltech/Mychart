package com.chatapp.repository

import com.chatapp.api.UserApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val api: UserApiService) {
    suspend fun login(username: String, password: String) =
        api.login(mapOf("username" to username, "password" to password))

    suspend fun register(username: String, password: String, role: String) =
        api.register(mapOf("username" to username, "password" to password, "role" to role))

    suspend fun getUserInfo(userId: String) = api.getUserInfo(userId)

    suspend fun updateUser(userId: String, newUsername: String, newPassword: String) =
        api.updateUser(mapOf("userId" to userId, "newUsername" to newUsername, "newPassword" to newPassword))

    suspend fun deleteUser(userId: String) = api.deleteUser(userId)
}
