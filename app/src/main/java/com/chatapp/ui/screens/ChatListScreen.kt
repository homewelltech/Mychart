package com.chatapp.ui.screens

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.chatapp.R
import com.chatapp.model.ChatItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController) {
    val chatList = remember {
        mutableStateListOf(
            ChatItem(userId = "1", username = "Alice", avatar = null, lastMessage = "Hello!", lastMessageTime = "10:24 AM", unreadCount = 2),
            ChatItem(userId = "2", username = "Bob", avatar = null, lastMessage = "See you later", lastMessageTime = "Yesterday", unreadCount = 0),
            ChatItem(userId = "3", username = "Charlie", avatar = null, lastMessage = "How’s it going?", lastMessageTime = "3 days ago", unreadCount = 1)
        )
    }

    // ✅ 让 `状态栏` 颜色和 `TopAppBar` 颜色一致
    val systemUiController = rememberSystemUiController()
    val topBarColor = MaterialTheme.colorScheme.primaryContainer // ✅ 颜色更柔和
    val useDarkIcons = !isSystemInDarkTheme() // ✅ 适配 `深色模式 & 浅色模式`

    LaunchedEffect(topBarColor) {
        systemUiController.setStatusBarColor(color = topBarColor, darkIcons = useDarkIcons)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(modifier = Modifier.systemBarsPadding()) {
                CenterAlignedTopAppBar(
                    title = { Text("Chats", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = topBarColor, // ✅ 让 `TopAppBar` 颜色和 `状态栏` 颜色一致
                        titleContentColor = Color.White
                    )
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surfaceVariant) // ✅ 让聊天列表背景更柔和
        ) {
            items(chatList) { chat ->
                ChatListItem(chat, navController)
                Divider(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}




@Composable
fun ChatListItem(chat: ChatItem, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current // ✅ 替换 `rememberRipple()`
            )
            { navController.navigate("chat/${chat.userId}") }
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = chat.avatar ?: R.drawable.default_avatar,
            contentDescription = "Chat Avatar",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.username,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = chat.lastMessage,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = chat.lastMessageTime,
                fontSize = 12.sp,
                color = Color.Gray
            )
            if (chat.unreadCount > 0) {
                Badge(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = Color.White,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = chat.unreadCount.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
