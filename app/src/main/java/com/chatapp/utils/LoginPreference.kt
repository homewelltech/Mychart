package com.chatapp.utils

import android.content.Context
import android.content.SharedPreferences

object LoginPreference {
    private const val PREFS_NAME = "login_prefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_PASSWORD = "password" // 注意：生产环境不要以明文方式保存密码
    private const val TOKEN = "Authorization"  //token 保存

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLoginInfo(context: Context, userId: String, password: String) {
        getPreferences(context).edit().apply {
            putString(KEY_USER_ID, userId)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }



    fun getUserId(context: Context): String? =
        getPreferences(context).getString(KEY_USER_ID, null)

    fun getPassword(context: Context): String? =
        getPreferences(context).getString(KEY_PASSWORD, null)

    fun saeToken(context: Context, token: String){
        getPreferences(context).edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun getToken(context: Context): String? =
        getPreferences(context).getString(TOKEN, null)
}
