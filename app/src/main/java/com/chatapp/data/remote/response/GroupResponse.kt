package com.chatapp.data.remote.response

data class GroupResponse(
    val total: Int,
    val members: List<GroupMember>
)

data class GroupMember(
    val userId: String,
    val nickname: String
)
