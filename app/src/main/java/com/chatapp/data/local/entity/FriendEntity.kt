package com.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend")
data class Friend(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val friendId: String,
    val nickname: String
)
