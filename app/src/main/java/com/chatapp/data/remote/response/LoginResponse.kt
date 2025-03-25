package com.chatapp.data.remote.response

data class LoginResponse(
    val token: String,
    val refreshToken: String,
    val userInfo: UserResponse
)
