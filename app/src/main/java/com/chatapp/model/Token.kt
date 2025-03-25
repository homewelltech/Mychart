package com.chatapp.model

data class Token(
    val token: String,
    val refreshToken: String? = null
)
