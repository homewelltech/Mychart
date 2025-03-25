package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.MessageRepository
import com.chatapp.data.remote.request.MessageRequest
import com.chatapp.model.Conversation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations

    fun loadConversations() {
        viewModelScope.launch {
            // 因为有了jwt令牌，都不用userId 了
//            messageRepository.loadRecentConver sations(currentUserId)
//                .collect { conversationList ->
//                    _conversations.value = conversationList
//                }
            val fakeConversations = listOf(
                Conversation("101", "Friend Alice", false, "Hey there!", System.currentTimeMillis(), 0),
                Conversation("102", "Friend Bob", false, "What's up?", System.currentTimeMillis() - 300000, 2),
                Conversation("103", "My Group #1", true, "Group message A", System.currentTimeMillis() - 600000, 1),
                Conversation("104", "Friend Carol", false, "Long time no see!", System.currentTimeMillis() - 900000, 0),
                Conversation("105", "Study Group", true, "Don't forget tomorrow's meeting", System.currentTimeMillis() - 1200000, 5),
                Conversation("106", "Friend David", false, "Check this out!", System.currentTimeMillis() - 1800000, 1),
                Conversation("107", "Hiking Buddies", true, "New plan for weekend hiking", System.currentTimeMillis() - 3600000, 3),
                Conversation("108", "Friend Eva", false, "Just finished reading that book", System.currentTimeMillis() - 7200000, 0),
                Conversation("109", "Cool Gang", true, "Movie night next Friday?", System.currentTimeMillis() - 10800000, 2),
                Conversation("110", "Friend Frank", false, "Let's catch up!", System.currentTimeMillis() - 14400000, 0),
            )

//            conversations.collect {
//                _conversations.value = fakeConversations
//            }

            


        }
    }




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
