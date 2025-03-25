package com.chatapp.data.remote.api

import com.chatapp.model.ApiResponse
import com.chatapp.data.remote.request.UserRequest
import com.chatapp.data.remote.request.LoginRequest
import com.chatapp.data.remote.request.RefreshRequest
import com.chatapp.data.remote.response.UserResponse
import com.chatapp.data.remote.response.LoginResponse
import com.chatapp.data.remote.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/user/register")
    suspend fun register(@Body request: UserRequest): Response<ApiResponse<UserResponse>>

    @POST("/api/user/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponse>>

    @POST("/api/user/refresh")
    suspend fun refreshToken(@Body request: RefreshRequest): Response<ApiResponse<TokenResponse>>

    @POST("/api/user/info")
    suspend fun getUserInfo(): Response<ApiResponse<UserResponse>>
}
