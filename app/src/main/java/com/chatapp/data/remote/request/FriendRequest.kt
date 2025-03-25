package com.chatapp.data.remote.request

data class FriendRequest(
    val userId: String?,      // 当前用户ID
    val friendUserId: String?,   // 好友ID（添加或删除时使用）
    val status: String,      // 操作状态，如 SENT 或 DELETED
    val page: Int? = null,   // 分页查询使用
    val size: Int? = null    // 分页查询使用
)
