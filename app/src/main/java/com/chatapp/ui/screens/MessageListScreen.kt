package com.chatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chatapp.model.Conversation
import com.chatapp.viewmodel.MessageViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatapp.R // 假设 R.drawable.* 有相关资源
import com.chatapp.ui.nav.Screen
import com.chatapp.utils.LoginPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageListScreen(
    navController: NavController,
    messageViewModel: MessageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var userId by remember { mutableStateOf(LoginPreference.getUserId(context) ?: "") }
    // 从 ViewModel 加载会话
    LaunchedEffect(userId) {
        messageViewModel.loadConversations()
    }

    val conversations by messageViewModel.conversations.collectAsState()

    // Scaffold 布局: 顶部采用 CenterAlignedTopAppBar, 内容区展示会话列表
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Messages") },
                colors = centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            // 示例：可在右下角添加一个“发起新聊天”按钮
            FloatingActionButton(
                onClick = {
                    // TODO: 跳转到好友选择界面、或创建新群组的界面等
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (conversations.isEmpty()) {
                Text(
                    text = "No conversations yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(conversations) { conversation ->
                        ConversationCard(
                            conversation = conversation,
                            onClick = {
                                // 点击跳转到 ChatScreen
                                navController.navigate(
                                    Screen.Chat.route +
                                            "?conversationId=${conversation.conversationId}" +
                                            "&isGroup=${conversation.isGroup}"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * 单个会话条目的美化组件，使用 ElevatedCard + 自定义排版。
 */
@Composable
fun ConversationCard(
    conversation: Conversation,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧头像/图标区域
            ConversationAvatar(conversation)

            Spacer(modifier = Modifier.width(12.dp))

            // 右侧文字区域
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // 会话名称（好友昵称或群组名）
                Text(
                    text = conversation.conversationName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // 最近一条消息
                Text(
                    text = conversation.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // 显示消息时间、未读数等
            Column(horizontalAlignment = Alignment.End) {
                val formattedTime = remember(conversation.lastMessageTime) {
                    formatTimestamp(conversation.lastMessageTime)
                }
                Text(
                    text = formattedTime,
                    style = MaterialTheme.typography.labelSmall
                )
                if (conversation.unreadCount > 0) {
                    // 用一个简单的气泡提示未读数
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = conversation.unreadCount.toString(),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

/**
 * 显示用户/群组头像。
 * 如果有头像 URL，可使用 Coil 的 AsyncImage 等替换。
 */
@Composable
fun ConversationAvatar(conversation: Conversation) {
    // 根据是否为群组，使用不同的占位图标
    val placeholder = if (conversation.isGroup) {
        R.drawable.default_avatar  // 示例资源
    } else {
        R.drawable.default_avatar   // 示例资源
    }

    // 可以让头像为圆形
    val shape: Shape = MaterialTheme.shapes.medium

    Image(
        painter = painterResource(id = placeholder),
        contentDescription = "Avatar",
        modifier = Modifier
            .size(48.dp)
            .clip(shape)
    )
}

/**
 * 将时间戳格式化为较简洁的字符串 (示例: "10:30" 或 "昨天" 或 "03/24").
 * 可根据项目需求实现更智能的格式化。
 */

fun formatTimestamp(timestamp: Long): String {
    // 这里只是示例写法
    // 可以使用 e.g. SimpleDateFormat, or any custom logic
    return java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
        .format(java.util.Date(timestamp))
}
