package com.chatapp.data.remote.request

data class GroupRequest(
    val groupId: String? = null,
    val name: String? = null,         // 群组名称（创建时使用）
    val ownerId: String? = null,      // 群主ID（创建时使用）
    val status: String? = null,       // 群组状态，如 ACTIVE
    val announcement: String? = null, // 群公告（更新公告时使用）
    val userId: String? = null,       // 操作成员时使用
    val memberStatus: String? = null, // 成员状态，如 MEMBER, ADMIN, REMOVED
    val page: Int? = null,            // 分页查询群成员时使用
    val size: Int? = null             // 分页查询群成员时使用
)
