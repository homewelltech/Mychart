package com.chatapp.data.remote.response

import com.chatapp.model.Friend

data class FriendResponse(
    val total: Int,
    val friends: List<Friend>
)

