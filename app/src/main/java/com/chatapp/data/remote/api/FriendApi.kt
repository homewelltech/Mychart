package com.chatapp.data.remote.api

import com.chatapp.model.ApiResponse
import com.chatapp.data.remote.request.FriendRequest
import com.chatapp.data.remote.response.FriendResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FriendApi {
    @POST("/api/friends/add")
    suspend fun addFriend(@Body request: FriendRequest): Response<ApiResponse<Any>>

    @POST("/api/friends/remove")
    suspend fun removeFriend(@Body request: FriendRequest): Response<ApiResponse<Any>>

    @POST("/api/friends")
    suspend fun getFriends(@Body request: FriendRequest): Response<ApiResponse<FriendResponse>>
}
