package com.chatapp.data.remote.api

import com.chatapp.model.ApiResponse
import com.chatapp.data.remote.request.ChatRoomRequest
import com.chatapp.data.remote.response.ChatRoomResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatRoomApi {
    @POST("/api/chatrooms/create")
    suspend fun createChatRoom(@Body request: ChatRoomRequest): Response<ApiResponse<Any>>

    @POST("/api/chatrooms/info")
    suspend fun getChatRoomInfo(@Body request: ChatRoomRequest): Response<ApiResponse<ChatRoomResponse>>
}
