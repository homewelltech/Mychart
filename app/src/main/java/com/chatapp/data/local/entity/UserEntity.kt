package com.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val userId: String,
    val nickname: String,
    val avatarUrl: String?,
    val role: String = "USER"
)
