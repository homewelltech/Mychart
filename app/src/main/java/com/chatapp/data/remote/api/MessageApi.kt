package com.chatapp.data.remote.api

import com.chatapp.model.ApiResponse
import com.chatapp.data.remote.request.MessageRequest
import com.chatapp.data.remote.response.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MessageApi {
    @POST("/api/messages/send")
    suspend fun sendMessage(@Body request: MessageRequest): Response<ApiResponse<Any>>

    @POST("/api/messages/send/media")
    suspend fun sendMediaMessage(@Body request: MessageRequest): Response<ApiResponse<Any>>

    @POST("/api/messages/send/file")
    suspend fun sendFileMessage(@Body request: MessageRequest): Response<ApiResponse<Any>>

    @POST("/api/messages/history")
    suspend fun getChatHistory(@Body request: MessageRequest): Response<ApiResponse<MessageResponse>>

    @POST("/api/messages/revoke")
    suspend fun revokeMessage(@Body request: MessageRequest): Response<ApiResponse<Any>>
}
