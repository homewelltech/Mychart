package com.chatapp.model

data class User(
    val userId: String,
    val nickname: String,
    val avatarUrl: String?,
    val role: String = "USER"
)
