package com.chatapp.utils

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREFS_NAME = "chat_prefs"
    private const val KEY_TOKEN = "token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String, refreshToken: String? = null) {
        preferences.edit().apply {
            putString(KEY_TOKEN, token)
            refreshToken?.let { putString(KEY_REFRESH_TOKEN, it) }
            apply()
        }
    }

    fun getToken(): String? = preferences.getString(KEY_TOKEN, null)
    fun getRefreshToken(): String? = preferences.getString(KEY_REFRESH_TOKEN, null)

    fun clearToken() {
        preferences.edit().clear().apply()
    }
}
