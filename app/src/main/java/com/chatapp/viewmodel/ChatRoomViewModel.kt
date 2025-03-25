package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.ChatRoomRepository
import com.chatapp.data.remote.request.ChatRoomRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository
) : ViewModel() {

    fun createChatRoom(name: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val request = ChatRoomRequest(name = name)
                val response = chatRoomRepository.createChatRoom(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun getChatRoomInfo(roomId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val request = ChatRoomRequest(roomId = roomId)
                val response = chatRoomRepository.getChatRoomInfo(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
