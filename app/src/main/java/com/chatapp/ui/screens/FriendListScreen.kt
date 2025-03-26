package com.chatapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatapp.ui.components.AddFriendDialog
import com.chatapp.viewmodel.FriendViewModel
import com.chatapp.utils.LoginPreference
import com.chatapp.ui.components.FriendItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListScreen(
    friendViewModel: FriendViewModel = hiltViewModel()
) {
    // 本地获取 userId（仅显示用途，不再传接口）
    val context = LocalContext.current
    var userId by remember { mutableStateOf(LoginPreference.getUserId() ?: "") }

    // 状态
    val friendList by friendViewModel.friendListState
    val isLoading by friendViewModel.isLoading
    val errorMessage by friendViewModel.errorMessage

    // 初始化时加载好友，无需 userId
    LaunchedEffect(Unit) {
        friendViewModel.loadFriends()
    }

    // 如果需要添加好友功能(对话框等)，可在这里写
    var showAddDialog by remember { mutableStateOf(false) }
    if (showAddDialog) {
        AddFriendDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { friendId ->
                friendViewModel.addFriend(friendId) { success ->
                    if (success) {
                        friendViewModel.loadFriends()
                    }
                }
                showAddDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Friend List (User: $userId)") // 如果想显示一下本地 userId
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                friendList.isEmpty() -> {
                    Text(
                        text = "No friends found",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(friendList) { friend ->
                            // 显示好友
                            FriendItem(
                                userId = friend.userId,
                                nickname = friend.nickname,
                                 onClick = {
                                     println("点击了 ${friend.nickname}  ${friend.userId}")
                                 }
                            )
                        }
                    }
                }
            }
        }
    }
}
