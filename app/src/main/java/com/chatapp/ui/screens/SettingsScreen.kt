package com.chatapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chatapp.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    var profileImage by remember { mutableStateOf<Uri?>(null) }
    val userInfo = remember { mutableStateOf(Pair("铁牛", "+852 4 635 9479")) }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { profileImage = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // 头像部分
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = profileImage ?: R.drawable.default_avatar,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.3f))
                    .border(2.dp, Color.LightGray, CircleShape)
                    .clickable { imagePickerLauncher.launch("image/*") }, // 选择头像
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape)
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Avatar", tint = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(userInfo.value.first, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(userInfo.value.second, fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(25.dp))

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Color.LightGray.copy(alpha = 0.5f)
        )

        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            SettingsItem(icon = Icons.Default.Person, title = "My Profile") { /* TODO: 进入个人信息 */ }
            SettingsItem(icon = Icons.Outlined.Favorite, title = "Saved Messages") { /* TODO: 进入保存消息 */ }
            SettingsItem(icon = Icons.Outlined.Phone, title = "Recent Calls") { /* TODO: 进入最近通话 */ }
            SettingsItem(icon = Icons.Outlined.Settings, title = "Devices") { /* TODO: 进入设备管理 */ }
            SettingsItem(icon = Icons.Outlined.Share, title = "Chat Folders") { /* TODO: 进入聊天文件夹 */ }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Log Out",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .clickable { /* TODO: 退出登录逻辑 */ }
        )
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 16.sp)
    }
}