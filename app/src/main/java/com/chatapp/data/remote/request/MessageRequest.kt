package com.chatapp.data.remote.request

data class MessageRequest(
    val type: String,               // 消息类型：TEXT, MEDIA, FILE
    val content: String,
    val senderId: String? = null,   // 发送者ID
    val receiverId: String? = null, // 接收者ID
    val status: String,             // 消息状态：SENT, DELIVERED, READ, REVOKED
    val timestamp: Long? = null,    // 时间戳（可由服务端生成）
    val messageId: Int? = null,     // 消息ID，撤回时使用
    val page: Int? = null,          // 分页查询使用
    val size: Int? = null           // 分页查询使用
)
