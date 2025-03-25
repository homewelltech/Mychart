package com.chatapp.repository

import com.chatapp.data.local.dao.FriendDao
import com.chatapp.data.remote.api.FriendApi
import com.chatapp.data.remote.request.FriendRequest
import javax.inject.Inject

class FriendRepository @Inject constructor(
    private val friendDao: FriendDao,
    private val friendApi: FriendApi
) {
    // 添加好友
    suspend fun addFriend(request: FriendRequest) =
        friendApi.addFriend(request)

    // 移除好友
    suspend fun removeFriend(request: FriendRequest) =
        friendApi.removeFriend(request)

    // 获取好友列表
    suspend fun getFriends(userId: String) =
        friendApi.getFriends(FriendRequest(userId, null, "GET"))
}
