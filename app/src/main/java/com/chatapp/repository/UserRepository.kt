// UserRepository.kt (数据管理层)
package com.chatapp.repository

import com.chatapp.api.UserApiService
import com.chatapp.api.AuthResponse
import com.chatapp.api.UserInfoResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val api: UserApiService) {
    suspend fun login(username: String, password: String): Response<AuthResponse> =
        api.login(mapOf("username" to username, "password" to password))

    suspend fun register(username: String, password: String, role: String): Response<AuthResponse> =
        api.register(mapOf("username" to username, "password" to password, "role" to role))

    suspend fun getUserInfo(): Response<UserInfoResponse> = api.getUserInfo()

    suspend fun updateUser(username: String, password: String): Response<UserInfoResponse> =
        api.updateUser(mapOf("username" to username, "password" to password))

    suspend fun deleteUser(): Response<Unit> = api.deleteUser()
}
