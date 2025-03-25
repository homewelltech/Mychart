package com.chatapp.model

data class Conversation(
    val conversationId: String,       // 对于联系人：userId；对于群：groupId
    val conversationName: String,     // 对于联系人：昵称；对于群：群名称
    val isGroup: Boolean,             // 是否为群聊
    val lastMessage: String,          // 最近一条消息的内容
    val lastMessageTime: Long,        // 最近消息的时间戳(毫秒)
    val unreadCount: Int = 0          // (可选) 未读消息数量
)
