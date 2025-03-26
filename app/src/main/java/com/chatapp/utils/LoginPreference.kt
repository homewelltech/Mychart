package com.chatapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.chatapp.utils.LoginPreference.preferences

object LoginPreference {
    private const val PREFS_NAME = "login_prefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_PASSWORD = "password" // 注意：生产环境不要以明文方式保存密码
    private const val TOKEN = "Authorization"  //token 保存
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


//    private fun getPreferences(context: Context): SharedPreferences =
//        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLoginInfo(context: Context, userId: String, password: String,token: String) {
        preferences.edit().apply {
            putString(KEY_USER_ID, userId)
            putString(KEY_PASSWORD, password)
            putString(TOKEN, token)
            apply()
        }
    }



    fun getUserId(): String? =
        preferences.getString(KEY_USER_ID, null)

    fun getPassword(): String? =
        preferences.getString(KEY_PASSWORD, null)



    fun getToken(): String? =
        preferences.getString(TOKEN, null)
}
