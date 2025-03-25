package com.chatapp.data.remote.api

import com.chatapp.model.ApiResponse
import com.chatapp.data.remote.request.GroupRequest
import com.chatapp.data.remote.response.GroupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GroupApi {
    @POST("/api/groups/create")
    suspend fun createGroup(@Body request: GroupRequest): Response<ApiResponse<Any>>

    @POST("/api/groups/addMember")
    suspend fun addMember(@Body request: GroupRequest): Response<ApiResponse<Any>>

    @POST("/api/groups/removeMember")
    suspend fun removeMember(@Body request: GroupRequest): Response<ApiResponse<Any>>

    @POST("/api/groups/setAnnouncement")
    suspend fun setAnnouncement(@Body request: GroupRequest): Response<ApiResponse<Any>>

    @POST("/api/groups/members")
    suspend fun getGroupMembers(@Body request: GroupRequest): Response<ApiResponse<GroupResponse>>
}
