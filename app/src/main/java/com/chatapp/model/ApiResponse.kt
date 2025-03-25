package com.chatapp.model

data class ApiResponse<T>(
    val timestamp: Long = System.currentTimeMillis(),
    val code: Int = 200,
    val message: String = "success",
    val data: T? = null
)
