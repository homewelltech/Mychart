package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.FriendRepository
import com.chatapp.data.remote.request.FriendRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    fun addFriend(userId: String, friendId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = friendRepository.addFriend(
                    FriendRequest(userId, friendId, "SENT")
                )
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun removeFriend(userId: String, friendId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = friendRepository.removeFriend(
                    FriendRequest(userId, friendId, "DELETED")
                )
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
