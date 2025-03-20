package com.chatapp.model

data class ChatItem(
    val userId: String,
    val username: String,
    val avatar: String?, // 头像 URL，若为空则使用默认头像
    val lastMessage: String, // 最后一条消息内容
    val lastMessageTime: String, // 最后一条消息的时间
    val unreadCount: Int // 未读消息数量
)