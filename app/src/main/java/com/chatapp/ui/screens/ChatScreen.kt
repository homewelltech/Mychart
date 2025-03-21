package com.chatapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.chatapp.R
import com.chatapp.model.ChatMessage

import com.chatapp.model.Friend
import com.chatapp.viewmodel.ChatViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChatScreen() {
    val fakeViewModel = remember { FakeChatViewModel() }
    ChatScreenPreview(userId = "1", viewModel = fakeViewModel)
}
@Composable
fun ChatScreenPreview(
    userId: String,
    viewModel: ChatViewModel
) {
    ChatScreen(userId = userId, viewModel = viewModel)
}

class FakeChatViewModel : ChatViewModel() {
    private val _fakeMessages = MutableStateFlow(
        listOf(
            ChatMessage("1", "你好 👋", "10:00", isSentByUser = false),
            ChatMessage("2", "Hi there!", "10:01", isSentByUser = true),
            ChatMessage("3", "欢迎使用预览模式", "10:02", isSentByUser = false)
        )
    )

    override val messages: StateFlow<List<ChatMessage>> = _fakeMessages

    override fun getUsername(userId: String): StateFlow<String> {
        return MutableStateFlow("预览用户")
    }

    override fun sendMessage(userId: String, text: String) {
        // 不需要处理，Preview 下无需发送逻辑
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userId: String,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val username by viewModel.getUsername(userId).collectAsState(initial = "Loading...")
    val messages by viewModel.messages.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current

    // ✅ 保证滚动到底部
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch { listState.scrollToItem(messages.lastIndex) }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ChatTopBar(username) }, // ✅ `TopAppBar` 固定
        bottomBar = {
            ChatInputField(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendMessage = {
                    if (messageText.text.isNotBlank()) {
                        viewModel.sendMessage(userId, messageText.text)
                        messageText = TextFieldValue("")
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp) // ✅ 让 `Scaffold` 忽略 `IME`
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() }) // ✅ 点击空白收起键盘
                }
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp) // ✅ 调整 `TopAppBar` 间距，视觉更协调
                    .windowInsetsPadding(WindowInsets.ime) // ✅ 让聊天内容适配键盘
            ) {
                items(messages) { message -> ChatBubble(message) }
            }
        }
    }
}
@Composable
fun ChatBubble(message: ChatMessage) {
    val isSentByUser = message.isSentByUser

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isSentByUser) {
            AsyncImage(
                model = message.senderAvatar ?: R.drawable.default_avatar,
                contentDescription = "Sender Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        Column(
            modifier = Modifier
                .background(
                    if (isSentByUser) MaterialTheme.colorScheme.primaryContainer else Color.White,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(12.dp)
                .widthIn(min = 40.dp, max = 280.dp) // ✅ 适配消息长度
        ) {
            Text(text = message.text, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message.timestamp,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}




@Composable
fun ChatInputField(
    messageText: TextFieldValue,
    onMessageChange: (TextFieldValue) -> Unit,
    onSendMessage: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant) // ✅ 让输入框背景更柔和
            .padding(8.dp)
            .imePadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = messageText,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            shape = MaterialTheme.shapes.large,
            placeholder = { Text("Type a message...") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = { onSendMessage() })
        )

        IconButton(
            onClick = onSendMessage,
            modifier = Modifier
                .size(50.dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White,
                modifier = Modifier.size(24.dp) // ✅ 让图标大小适配
            )
        }
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(username: String) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* TODO: 跳转个人信息 */ }
            ) {
                AsyncImage(
                    model = R.drawable.default_avatar,
                    contentDescription = "Chat Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(username, fontSize = 18.sp, color = Color.White)
                    Text("last seen recently", fontSize = 12.sp, color = Color.LightGray)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // ✅ 颜色适配 Material3
            titleContentColor = Color.White
        )
    )
}
