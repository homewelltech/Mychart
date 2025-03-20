package com.chatapp.model

data class ChatMessage(
    val id: String,
    val text: String,
    val timestamp: String,
    val isSentByUser: Boolean,
    val senderAvatar: String? = null
)
