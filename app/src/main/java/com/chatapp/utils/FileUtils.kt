package com.chatapp.utils

import android.content.Context
import java.io.File

object FileUtils {

    /**
     * 获取应用缓存目录
     */
    fun getCacheDir(context: Context): File = context.cacheDir

    /**
     * 保存文本到文件中
     */
    fun saveTextToFile(context: Context, filename: String, content: String): Boolean {
        return try {
            val file = File(getCacheDir(context), filename)
            file.writeText(content)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 从文件中读取文本
     */
    fun readTextFromFile(context: Context, filename: String): String? {
        return try {
            val file = File(getCacheDir(context), filename)
            if (file.exists()) file.readText() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
