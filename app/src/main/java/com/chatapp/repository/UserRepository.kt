package com.chatapp.repository

import com.chatapp.data.local.dao.UserDao
import com.chatapp.data.remote.api.UserApi
import com.chatapp.data.remote.request.LoginRequest
import com.chatapp.data.remote.request.UserRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi
) {

    // 用户登录
    suspend fun login(userId: String, password: String) =
        userApi.login(LoginRequest(userId, password))

    // 用户注册
    suspend fun register(userRequest: UserRequest) =
        userApi.register(userRequest)

    // 获取本地用户信息
    suspend fun getUser(userId: String) = userDao.getUserById(userId)

    // 保存用户信息到本地数据库
    suspend fun saveUser(user: com.chatapp.data.local.entity.User) =
        userDao.insertUser(user)
}
