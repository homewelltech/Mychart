package com.chatapp.model

data class Friend(
    val userId: String,
    val username: String,
    val avatar: String?,  // 可为空，表示没有头像
    val lastSeen: String,
    val isOnline: Boolean=false
)