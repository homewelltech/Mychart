package com.chatapp.data.remote.request

data class FriendRequest(
    val page: Int? = 0,   // 分页查询使用
    val size: Int? = 10    // 分页查询使用
)
