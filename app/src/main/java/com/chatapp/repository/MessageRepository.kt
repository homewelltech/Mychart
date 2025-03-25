package com.chatapp.repository

import com.chatapp.data.local.dao.MessageDao
import com.chatapp.data.remote.api.MessageApi
import com.chatapp.data.remote.request.MessageRequest
import com.chatapp.model.Conversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val messageApi: MessageApi
) {
    // 发送消息
    suspend fun sendMessage(request: MessageRequest) =
        messageApi.sendMessage(request)

    // 发送媒体消息
    suspend fun sendMediaMessage(request: MessageRequest) =
        messageApi.sendMediaMessage(request)

    // 发送文件消息
    suspend fun sendFileMessage(request: MessageRequest) =
        messageApi.sendFileMessage(request)

    // 获取聊天记录
    suspend fun getChatHistory(request: MessageRequest) =
        messageApi.getChatHistory(request)

    // 撤回消息
    suspend fun revokeMessage(request: MessageRequest) =
        messageApi.revokeMessage(request)

    // 保存消息到本地数据库
    suspend fun saveMessage(message: com.chatapp.data.local.entity.Message) =
        messageDao.insertMessage(message)

    // 获取本地聊天记录
    suspend fun getChatMessages(userId: String, otherId: String) =
        messageDao.getChatMessages(userId, otherId)



    fun loadRecentConversations(currentUserId: String): Flow<List<Conversation>> = flow {
        // 假设您已经在 MessageRepository 中实现了对本地数据库或远端接口的获取逻辑
        // 例如：
        // 1) friendApi.getFriendList(currentUserId)
        // 2) groupApi.getGroupList(currentUserId)
        // 3) 各自获取最后一条消息
        // 4) 最后合并、排序
        val conversations = listOf<Conversation>()
        emit(conversations)
    }

}
