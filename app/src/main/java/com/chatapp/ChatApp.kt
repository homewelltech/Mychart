package com.chatapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChatApp : Application() {
    // 全局初始化逻辑（如初始化日志、第三方库等）
}
