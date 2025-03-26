package com.chatapp.repository

import com.chatapp.data.local.dao.FriendDao
import com.chatapp.data.remote.api.FriendApi
import com.chatapp.data.remote.request.FriendRequest
import com.chatapp.data.remote.response.FriendResponse
import com.chatapp.model.ApiResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * FriendRepository 负责管理好友相关的网络请求和本地数据库逻辑（如有需要）。
 * - 后端通过 JWT 获取当前用户，因此不再需要在请求体中显式带 userId。
 * - 如果需要本地缓存，可结合 friendDao 进行插入/查询/删除操作。
 */
class FriendRepository @Inject constructor(
    private val friendApi: FriendApi,
    private val friendDao: FriendDao
) {

    /**
     * 获取好友列表（无需 userId，JWT 决定当前用户）。
     * 如果后端返回的 JSON 数据结构与此不同，请在泛型或响应模型中做相应调整。
     */
    suspend fun getFriends(): Response<ApiResponse<FriendResponse>> {
        // 通过 FriendRequest 可以只传必要字段（若后端需要，可传 status="GET" 或 friendId=null）
        // 也可直接定义一个无参接口，如 friendApi.getFriends()。
        return friendApi.getFriends(FriendRequest(0, 10))
    }

    /**
     * 添加好友，需要对方的 friendId，后端通过 JWT 确定本用户。
     * @param request: FriendRequest(friendId=xxx, status="SENT")
     */
    suspend fun addFriend(request: FriendRequest): Response<ApiResponse<Any>> {
        return friendApi.addFriend(request)
    }

    /**
     * 移除好友，需要对方 friendId，后端通过 JWT 确定本用户。
     * @param request: FriendRequest(friendId=xxx, status="DELETED")
     */
    suspend fun removeFriend(request: FriendRequest): Response<ApiResponse<Any>> {
        return friendApi.removeFriend(request)
    }

    // 若需要离线缓存功能，可在此与 friendDao 进行本地操作，例如：
    // suspend fun getLocalFriends(): List<FriendEntity> = friendDao.getAll()
    // suspend fun insertLocalFriends(friends: List<FriendEntity>) = friendDao.insertAll(friends)
}
