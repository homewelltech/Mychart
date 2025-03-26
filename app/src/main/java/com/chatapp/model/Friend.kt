package com.chatapp.model

data class Friend(
    val userId: String,
    val nickname: String,
    val avatarUrl: String? = null

)
