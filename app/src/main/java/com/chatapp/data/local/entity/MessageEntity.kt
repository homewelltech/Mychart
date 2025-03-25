package com.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Int = 0,
    val type: String,        // TEXT、MEDIA、FILE 等
    val content: String,
    val senderId: String,
    val receiverId: String,
    val status: String,      // SENT、DELIVERED、READ、REVOKED 等
    val timestamp: Long
)
