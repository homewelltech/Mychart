package com.chatapp.data.remote.response

data class ChatRoomResponse(
    val roomId: String,
    val name: String,
    val lastMessage: String?,
    val updatedAt: String // 使用 ISO 格式时间字符串
)
