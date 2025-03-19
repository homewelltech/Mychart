package com.chatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.chatapp.R
import com.chatapp.model.ChatItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController) {
    val chatList = remember {
        mutableStateListOf(
            ChatItem(username = "Senlin。龙岗", avatar = null, lastMessage = "bir的", lastMessageTime = "11/24/24", unreadCount = 0),
            ChatItem(username = "Mm (涉及资金往来请语音)", avatar = null, lastMessage = "bir的看看还能下户不能", lastMessageTime = "11/17/24", unreadCount = 2),
            ChatItem(username = "雷神 索尔", avatar = null, lastMessage = "雷神 uses a self-destruct timer", lastMessageTime = "11/03/24", unreadCount = 1),
            ChatItem(username = "哈哈 😺", avatar = null, lastMessage = "好吧", lastMessageTime = "11/03/24", unreadCount = 0),
            ChatItem(username = "fb粉海外粉谷歌粉引流", avatar = null, lastMessage = "10w+的引流账号群控私信", lastMessageTime = "04/29/24", unreadCount = 3),
            ChatItem(username = "麦芽互动 盖纸 FB谷歌TK", avatar = null, lastMessage = "台湾死户都是常见的", lastMessageTime = "10/21/24", unreadCount = 0),
            ChatItem(username = "魔王金流【天下】", avatar = null, lastMessage = "1", lastMessageTime = "09/29/24", unreadCount = 10)
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Chats", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* 添加新聊天逻辑 */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "New Chat", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF25D366), // 适配 WhatsApp 风格
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFEFEFEF)) // 柔和背景
        ) {
            items(chatList) { chat ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 12.dp) // 调整间距
                        .clickable { navController.navigate("chat/${chat.username}") }
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = chat.avatar ?: R.drawable.default_avatar,
                        contentDescription = "Chat Avatar",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.Gray)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(text = chat.username, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text(
                            text = chat.lastMessage,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = chat.lastMessageTime, fontSize = 12.sp, color = Color.Gray)
                        if (chat.unreadCount > 0) {
                            Badge {
                                Text(text = "${chat.unreadCount}")
                            }
                        }
                    }
                }
            }
        }
    }
}
