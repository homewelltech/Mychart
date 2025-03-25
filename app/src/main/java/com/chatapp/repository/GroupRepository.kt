package com.chatapp.repository

import com.chatapp.data.remote.api.GroupApi
import com.chatapp.data.remote.request.GroupRequest
import javax.inject.Inject

class GroupRepository @Inject constructor(
    private val groupApi: GroupApi
) {
    // 创建群组
    suspend fun createGroup(request: GroupRequest) =
        groupApi.createGroup(request)

    // 添加群组成员
    suspend fun addMember(request: GroupRequest) =
        groupApi.addMember(request)

    // 移除群组成员
    suspend fun removeMember(request: GroupRequest) =
        groupApi.removeMember(request)

    // 更新群公告
    suspend fun setAnnouncement(request: GroupRequest) =
        groupApi.setAnnouncement(request)

    // 获取群组成员列表
    suspend fun getGroupMembers(request: GroupRequest) =
        groupApi.getGroupMembers(request)
}
