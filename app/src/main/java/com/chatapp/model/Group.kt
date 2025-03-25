package com.chatapp.model

data class Group(
    val groupId: String,
    val name: String,
    val ownerId: String,
    val status: String = "ACTIVE"
)
