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
import androidx.navigation.compose.rememberNavController
import com.chatapp.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewContactListScreen() {
    // 使用一个假的 NavController 来预览
    val navController = rememberNavController()
    ContactListScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavController) {
    val contactList = remember {
        mutableStateListOf(
            Friend("1", "Alice", null, isOnline = false, lastSeen = "6 hours ago"),
            Friend("2", "Bob", null, isOnline = false, lastSeen = "7 hours ago"),
            Friend("3", "Charlie", null, isOnline = true, lastSeen = "Recently")
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Contacts", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {
                        // TODO: 添加联系人逻辑，例如跳转 add screen
                        navController.navigate("add_friend")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Contact",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFEFEFEF))
        ) {
            items(contactList) { friend ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("chat/${friend.userId}") }
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
                        Text(
                            text = friend.username,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
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
