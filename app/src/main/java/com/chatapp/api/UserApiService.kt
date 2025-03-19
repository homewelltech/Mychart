package com.chatapp.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path


interface UserApiService {
    @POST("/api/user/register")
    suspend fun register(@Body request: Map<String, String>): Map<String, String>

    @POST("/api/user/login")
    suspend fun login(@Body request: Map<String, String>): Map<String, String>

    @GET("/api/user/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: String): Map<String, String>

    @PUT("/api/user/update")
    suspend fun updateUser(@Body request: Map<String, String>): Map<String, String>

    @DELETE("/api/user/delete/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String): Map<String, String>
}
