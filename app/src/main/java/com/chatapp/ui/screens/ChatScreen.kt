package com.chatapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction

enum class MessageType {
    TEXT, IMAGE, AUDIO, VIDEO
}

data class Message(
    val id: String,
    val text: String = "",
    val isOwnMessage: Boolean = false,
    val type: MessageType = MessageType.TEXT
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    conversationId: String?,
    nikeName: String? = "",
    isGroup: Boolean = false
) {
    var messageText by remember { mutableStateOf("") }
    var messages by remember {
        mutableStateOf(
            listOf(
                Message("1", "Hi, how are you?", isOwnMessage = false, type = MessageType.TEXT),
                Message("2", "I'm fine, thank you!", isOwnMessage = true, type = MessageType.TEXT),
                Message("3", "What are you up to today?", isOwnMessage = false, type = MessageType.TEXT)
            )
        )
    }
    val focusManager = LocalFocusManager.current

    // 渐变背景提升整体视觉效果
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.primaryContainer)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text(nikeName.toString()) }) },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                // 点击空白处清除焦点，收起键盘
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .background(backgroundBrush)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // 聊天消息列表
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(messages) { message ->
                        MessageRow(message = message)
                    }
                }

                Divider()

                // 底部输入区（文字输入+发送按钮）以及附件按钮行
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .imePadding(), // 键盘弹出时自动增加内边距
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            modifier = Modifier
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
                                .clip(MaterialTheme.shapes.small),
                            placeholder = { Text("Type a message...") },
                            colors = outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                            keyboardActions = KeyboardActions(
                                onSend = {
                                    if (messageText.isNotBlank()) {
                                        messages = messages + Message(
                                            id = (messages.size + 1).toString(),
                                            text = messageText,
                                            isOwnMessage = true,
                                            type = MessageType.TEXT
                                        )
                                        messageText = ""
                                    }
                                }
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (messageText.isNotBlank()) {
                                    messages = messages + Message(
                                        id = (messages.size + 1).toString(),
                                        text = messageText,
                                        isOwnMessage = true,
                                        type = MessageType.TEXT
                                    )
                                    messageText = ""
                                }
                            },
                            enabled = messageText.isNotBlank(),
                            shape = CircleShape,
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Send", color = Color.White)
                        }
                    }
                    // 附件按钮行：图片、语音、视频
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            onClick = {
                                // 模拟发送图片消息
                                messages = messages + Message(
                                    id = (messages.size + 1).toString(),
                                    isOwnMessage = true,
                                    type = MessageType.IMAGE
                                )
                            }
                        ) {
                            Icon(Icons.Filled.Image, contentDescription = "Send Image")
                        }
                        IconButton(
                            onClick = {
                                // 模拟发送语音消息
                                messages = messages + Message(
                                    id = (messages.size + 1).toString(),
                                    isOwnMessage = true,
                                    type = MessageType.AUDIO
                                )
                            }
                        ) {
                            Icon(Icons.Filled.Mic, contentDescription = "Send Voice")
                        }
                        IconButton(
                            onClick = {
                                // 模拟发送视频消息
                                messages = messages + Message(
                                    id = (messages.size + 1).toString(),
                                    isOwnMessage = true,
                                    type = MessageType.VIDEO
                                )
                            }
                        ) {
                            Icon(Icons.Filled.Videocam, contentDescription = "Send Video")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MessageRow(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isOwnMessage) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!message.isOwnMessage) {
            // 对方消息显示默认头像
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Friend Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(8.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        // 消息气泡，根据消息类型显示不同内容
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = if (message.isOwnMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Box(modifier = Modifier.padding(8.dp)) {
                when (message.type) {
                    MessageType.TEXT -> {
                        Text(
                            text = message.text,
                            color = if (message.isOwnMessage) Color.White else Color.Black
                        )
                    }
                    MessageType.IMAGE -> {
                        Icon(
                            imageVector = Icons.Filled.Image,
                            contentDescription = "Image Message",
                            tint = if (message.isOwnMessage) Color.White else Color.Black,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    MessageType.AUDIO -> {
                        Icon(
                            imageVector = Icons.Filled.Mic,
                            contentDescription = "Audio Message",
                            tint = if (message.isOwnMessage) Color.White else Color.Black,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    MessageType.VIDEO -> {
                        Icon(
                            imageVector = Icons.Filled.Videocam,
                            contentDescription = "Video Message",
                            tint = if (message.isOwnMessage) Color.White else Color.Black,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}
