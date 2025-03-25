package com.chatapp.repository

import com.chatapp.data.local.dao.ChatRoomDao
import com.chatapp.data.remote.api.ChatRoomApi
import com.chatapp.data.remote.request.ChatRoomRequest
import javax.inject.Inject

class ChatRoomRepository @Inject constructor(
    private val chatRoomDao: ChatRoomDao,
    private val chatRoomApi: ChatRoomApi
) {
    // 创建聊天室
    suspend fun createChatRoom(request: ChatRoomRequest) =
        chatRoomApi.createChatRoom(request)

    // 获取聊天室信息
    suspend fun getChatRoomInfo(request: ChatRoomRequest) =
        chatRoomApi.getChatRoomInfo(request)

    // 保存聊天室信息到本地数据库
    suspend fun saveChatRoom(chatRoom: com.chatapp.data.local.entity.ChatRoom) =
        chatRoomDao.insertChatRoom(chatRoom)

    // 获取本地聊天室信息
    suspend fun getChatRoomById(roomId: String) =
        chatRoomDao.getChatRoomById(roomId)
}
