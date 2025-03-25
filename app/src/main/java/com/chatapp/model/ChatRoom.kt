package com.chatapp.model

data class ChatRoom(
    val id: String,
    val name: String,
    val lastMessage: String?,
    val updatedAt: Long
)
