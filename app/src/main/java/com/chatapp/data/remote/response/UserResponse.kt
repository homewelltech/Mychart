package com.chatapp.data.remote.response

data class UserResponse(
    val userId: String,
    val nickname: String,
    val avatarUrl: String?,
    val role: String
)
