package com.chatapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chatapp.ui.components.MessageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(

    conversationId: String?,
    isGroup: Boolean=false

) {
    var messageText by remember { mutableStateOf("") }
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.primaryContainer)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Chat") }) },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
//                    items(messages) { message ->
//                        MessageItem(message = message, isOwnMessage = false)
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Type a message...") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                messageText = ""
                            }
                        },
                        enabled = messageText.isNotBlank(),
                        shape = CircleShape,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Send")
                    }
                }

            }
        }
    }
}
