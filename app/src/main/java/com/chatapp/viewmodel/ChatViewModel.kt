// ChatViewModel.kt (聊天逻辑处理)
package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    fun sendMessage(message: String) {
        viewModelScope.launch {
            // 处理消息发送逻辑 (未来可集成 WebSocket)
            println("Sending message: $message")
        }
    }
}