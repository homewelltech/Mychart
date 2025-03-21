// UserApiService.kt (API 接口)
package com.chatapp.api

import com.chatapp.model.Friend
import com.chatapp.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Query

// 定义 API 响应模型
data class AuthResponse(val token: String, val refreshToken: String)
data class UserInfoResponse(val id: String, val username: String, val email: String)



interface UserApiService {

    /**
     * 用户登录
     */
    @POST("/api/auth/login")
    suspend fun login(@Body request: Map<String, String>): Response<AuthResponse>

    /**
     * 获取当前用户信息
     */
    @GET("/api/user/info")
    suspend fun getUserInfo(): Response<User>

    /**
     * 获取好友列表
     */
    @GET("/api/friends")
    suspend fun getFriends(): Response<List<Friend>>

    /**
     * 添加好友
     */
    @POST("/api/friends/add")
    suspend fun addFriend(@Body request: Map<String, String>): Response<Unit>

    /**
     * 删除好友
     */
    @DELETE("/api/friends/remove")
    suspend fun removeFriend(@Query("friendUsername") friendUsername: String): Response<Unit>

    /**
     * 用户登出
     */
    @POST("/api/auth/logout")
    suspend fun logout(): Response<Unit>



    @Multipart
    @POST("/api/avatar/upload")
    suspend fun uploadAvatar(
        @Part("userId") userId: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<Map<String, String>>


    @GET("/api/avatar/get")
    suspend fun getAvatar(@Query("userId") userId: String): Response<Map<String, String>> // 假设返回：{ "avatarUrl": "http://..." }

    @GET("/api/avatar/default")
    suspend fun getDefaultAvatar(): Response<Map<String, String>> // 假设返回：{ "avatarUrl": "http://..." }
}
