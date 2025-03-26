package com.chatapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.model.Friend
import com.chatapp.data.remote.request.FriendRequest
import com.chatapp.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * FriendViewModel 管理好友列表的加载、添加和删除逻辑。
 * 后端可通过 JWT 获取当前用户，不需要前端传 userId。
 */
@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    // 好友列表状态
    private val _friendListState: MutableState<List<Friend>> = mutableStateOf(emptyList())
    val friendListState: State<List<Friend>> = _friendListState

    // 是否正在请求
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // 错误信息
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    /**
     * 从后端获取好友列表（无需 userId，后台通过 JWT 确定当前用户）
     */
    fun loadFriends() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                // 调用 Repository 获取好友列表
                val response = friendRepository.getFriends()
                println("response.isSuccessful ${response.isSuccessful}")
                if (response.isSuccessful) {
                    // 假设后端返回 data.friends: List<Friend>
                    print("拿到数据了  1")

                    val friendList = response.body()?.data?.content ?: emptyList()
                    _friendListState.value = friendList
                    println("拿到数据了 ${friendList.toString()}")
                } else {
                    _errorMessage.value = "Failed to fetch friend list: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * 添加好友，需要传递对方 friendUserId。
     * 后端使用 JWT 确定当前用户，FriendRequest 中 userId 可为 null。
     */
    fun addFriend(friendId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // 构造请求体，status='SENT'
                val request = FriendRequest(

                )
                val response = friendRepository.addFriend(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    /**
     * 移除好友，需要传递对方 friendId。
     * 后端使用 JWT 确定当前用户，FriendRequest 中 userId 可为 null。
     */
    fun removeFriend(friendId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // 构造请求体，status='DELETED'
                val request = FriendRequest(

                )
                val response = friendRepository.removeFriend(request)
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
