// UserApiService.kt (API 接口)
package com.chatapp.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE

// 定义 API 响应模型
data class AuthResponse(val token: String, val refreshToken: String)
data class UserInfoResponse(val id: String, val username: String, val email: String)

interface UserApiService {
    @POST("/api/user/register")
    suspend fun register(@Body request: Map<String, String>): Response<AuthResponse>

    @POST("/api/auth/login")
    suspend fun login(@Body request: Map<String, String>): Response<AuthResponse>

    @POST("/api/auth/refresh")
    suspend fun refresh(@Body request: Map<String, String>): Response<AuthResponse>

    @GET("/api/user/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>

    @PUT("/api/user/update")
    suspend fun updateUser(@Body request: Map<String, String>): Response<UserInfoResponse>

    @DELETE("/api/user/delete")
    suspend fun deleteUser(): Response<Unit>
}
