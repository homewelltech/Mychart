package com.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatroom")
data class ChatRoom(
    @PrimaryKey val id: String,
    val name: String,
    val lastMessage: String?,
    val updatedAt: Long
)
