package com.chatapp.utils

import android.util.Log
import android.widget.Toast
import android.content.Context

object ErrorHandler {
    private const val TAG = "ErrorHandler"

    /**
     * 打印错误日志
     */
    fun logError(error: Throwable) {
        Log.e(TAG, "Error occurred: ${error.message}", error)
    }

    /**
     * 显示错误提示给用户
     */
    fun showError(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * 统一处理异常
     */
    fun handleException(context: Context, error: Throwable, userMessage: String = "发生错误，请稍后重试") {
        logError(error)
        showError(context, userMessage)
    }
}
