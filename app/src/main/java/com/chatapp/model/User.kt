package com.chatapp.model

data class User(
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val status: String
)