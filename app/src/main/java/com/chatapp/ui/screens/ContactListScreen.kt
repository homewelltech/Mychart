package com.chatapp.ui.screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.chatapp.viewmodel.UserViewModel
import com.chatapp.model.Friend
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import com.chatapp.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavController) {
    val contactList = remember {
        mutableStateListOf(
            Friend(username = "君尚", avatar = null, isOnline = false, lastSeen = "6 hours ago"),
            Friend(username = "王宁", avatar = null, isOnline = false, lastSeen = "7 hours ago"),
            Friend(username = "凌峰", avatar = null, isOnline = false, lastSeen = "Yesterday at 10:36 PM"),
            Friend(username = "金陵", avatar = null, isOnline = false, lastSeen = "01/17/25"),
            Friend(username = "金陵", avatar = null, isOnline = false, lastSeen = "01/17/25"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "夏夏", avatar = null, isOnline = false, lastSeen = "11/28/24"),
            Friend(username = "建林", avatar = null, isOnline = false, lastSeen = "10/12/24"),
            Friend(username = "元气森林", avatar = null, isOnline = true, lastSeen = "Recently")
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Contacts", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* 添加好友逻辑 */ }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Friend", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF62D78E), // 更柔和的绿色
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFEFEFEF)) // 更柔和的背景颜色
        ) {
            items(contactList) { friend ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 12.dp) // 缩小间距
                        .clickable { navController.navigate("chat/${friend.username}") }
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = friend.avatar ?: R.drawable.default_avatar,
                        contentDescription = "Contact Avatar",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.Gray)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(text = friend.username, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text(
                            text = if (friend.isOnline) "Online" else "last seen ${friend.lastSeen}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}