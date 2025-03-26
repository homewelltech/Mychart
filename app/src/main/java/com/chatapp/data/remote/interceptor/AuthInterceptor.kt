package com.chatapp.data.remote.interceptor

import androidx.compose.ui.platform.LocalContext
import com.chatapp.utils.LoginPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * AuthInterceptor：为每个请求添加 "Authorization: Bearer <token>"，
 * 排除登录/注册请求（可根据实际接口路径进行判断）。
 *
 * 依赖一个 TokenManager（或从 Preference 获取 Token），
 * 以获取当前存储的 token。
 */
class AuthInterceptor @Inject constructor(

) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val url = originalRequest.url.encodedPath // "/api/user/login", "/api/friends", etc.

        // 如果是登录或注册接口，不加 Authorization 头
        if (url.contains("/api/user/login") || url.contains("/api/user/register")) {
            return chain.proceed(originalRequest)
        }

        // 其余接口都加上 Authorization 头（若 token 存在）
        val newRequest = originalRequest.newBuilder().apply {

            header("Authorization", "Bearer ${LoginPreference.getToken()}")

        }.build()

        return chain.proceed(newRequest)
    }
}
