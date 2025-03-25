package com.chatapp.data.remote.request

data class UserRequest(
    val nickname: String,
    val password: String,
    val role: String = "USER"
)
