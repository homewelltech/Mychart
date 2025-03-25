package com.chatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatapp.repository.GroupRepository
import com.chatapp.data.remote.request.GroupRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : ViewModel() {

    fun createGroup(name: String, ownerId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = groupRepository.createGroup(
                    GroupRequest(name = name, ownerId = ownerId, status = "ACTIVE")
                )
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun addMember(groupId: String, userId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = groupRepository.addMember(
                    GroupRequest(groupId = groupId, userId = userId, memberStatus = "MEMBER")
                )
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun removeMember(groupId: String, userId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = groupRepository.removeMember(
                    GroupRequest(groupId = groupId, userId = userId, memberStatus = "REMOVED")
                )
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
