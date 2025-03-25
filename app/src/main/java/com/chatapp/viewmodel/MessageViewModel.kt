package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.MessageRepository
import com.chatapp.data.remote.request.MessageRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun sendMessage(
        type: String,
        content: String,
        senderId: String,
        receiverId: String,
        status: String = "SENT",
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = MessageRequest(type, content, senderId, receiverId, status)
                val response = messageRepository.sendMessage(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun getChatHistory(
        userId: String,
        otherId: String,
        page: Int,
        size: Int,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = MessageRequest(
                    type = "HISTORY",
                    content = "",
                    senderId = userId,
                    receiverId = otherId,
                    status = "GET",
                    page = page,
                    size = size
                )
                val response = messageRepository.getChatHistory(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun revokeMessage(
        messageId: Int,
        senderId: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = MessageRequest(
                    type = "REVOKE",
                    content = "",
                    senderId = senderId,
                    receiverId = "",
                    status = "REVOKED",
                    messageId = messageId
                )
                val response = messageRepository.revokeMessage(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
