package com.chatapp.data.remote.response

data class FriendResponse(
    val total: Int,
    val friends: List<FriendInfo>
)

data class FriendInfo(
    val userId: String,
    val nickname: String
)
