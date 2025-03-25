package com.chatapp.data.remote.response

data class MessageResponse(
    val total: Int,
    val messages: List<MessageInfo>
)

data class MessageInfo(
    val messageId: Int,
    val type: String,
    val content: String,
    val senderId: String,
    val receiverId: String,
    val status: String,
    val timestamp: String // 使用 ISO 格式字符串表示时间
)
