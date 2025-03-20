package com.chatapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.model.ChatItem
import com.chatapp.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList()) // ✅ 确保 `messages` 是 `List<ChatMessage>`
    val messages: StateFlow<List<ChatMessage>> = _messages


    // 🔹 模拟存储用户 ID 与名称
    private val userMap = mapOf(
        "1" to "Alice",
        "2" to "Bob",
        "3" to "Charlie"
    )
    val chatList =
        mutableStateListOf(
            ChatItem(userId = "4", username = "Senlin。龙岗", avatar = null, lastMessage = "bir的", lastMessageTime = "11/24/24", unreadCount = 0),
            ChatItem(userId = "5", username = "Mm (涉及资金往来请语音)", avatar = null, lastMessage = "bir的看看还能下户不能", lastMessageTime = "11/17/24", unreadCount = 2),
            ChatItem(userId = "6", username = "雷神 索尔", avatar = null, lastMessage = "雷神 uses a self-destruct timer", lastMessageTime = "11/03/24", unreadCount = 1),
            ChatItem(userId = "7", username = "哈哈 😺", avatar = null, lastMessage = "好吧", lastMessageTime = "11/03/24", unreadCount = 0),
            ChatItem(userId = "8", username = "fb粉海外粉谷歌粉引流", avatar = null, lastMessage = "10w+的引流账号群控私信", lastMessageTime = "04/29/24", unreadCount = 3),
            ChatItem(userId = "9", username = "麦芽互动 盖纸 FB谷歌TK", avatar = null, lastMessage = "台湾死户都是常见的", lastMessageTime = "10/21/24", unreadCount = 0),
            ChatItem(userId = "10", username = "魔王金流【天下】", avatar = null, lastMessage = "1", lastMessageTime = "09/29/24", unreadCount = 10) ,

            ChatItem(userId = "1", username = "Alice", avatar = null, lastMessage = "Hello!", lastMessageTime = "10:24 AM", unreadCount = 2),
            ChatItem(userId = "2", username = "Bob", avatar = null, lastMessage = "See you later", lastMessageTime = "Yesterday", unreadCount = 0),
            ChatItem(userId = "3", username = "Charlie", avatar = null, lastMessage = "How’s it going?", lastMessageTime = "3 days ago", unreadCount = 1)
        )


    fun getUsername(userId: String): StateFlow<String> {
//        val username = userMap[userId] ?: "Unknown User"
        val username= chatList.last { it.userId == userId }.username
        return MutableStateFlow(username) // ✅ 确保 UI 监听用户名变化
    }

    fun sendMessage(userId: String, text: String) {
        val newMessage = ChatMessage(
            id = (_messages.value.size + 1).toString(),
            text = text,
            timestamp = "Now",
            isSentByUser = true
        )
        _messages.value = _messages.value + newMessage
    }
}
