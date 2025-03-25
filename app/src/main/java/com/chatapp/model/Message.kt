package com.chatapp.model

data class Message(
    val messageId: Int,
    val type: String,      // TEXT, MEDIA, FILE 等
    val content: String,
    val senderId: String,
    val receiverId: String,
    val status: String,    // SENT, DELIVERED, READ, REVOKED 等
    val timestamp: Long
)
