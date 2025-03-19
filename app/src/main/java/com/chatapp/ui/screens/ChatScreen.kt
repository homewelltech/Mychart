// ChatScreen.kt (聊天界面)
package com.chatapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chatapp.viewmodel.ChatViewModel

@Composable
fun ChatScreen(navController: NavController, userId: String, viewModel: ChatViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Chat with $userId", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
    }
}
