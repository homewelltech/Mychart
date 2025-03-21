package com.chatapp.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.chatapp.R
import com.chatapp.viewmodel.UserViewModel


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewSettingsScreen() {
//    // 使用一个假的 NavController 来预览
//    val navController = rememberNavController()
//    SettingsScreen(navController)
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: UserViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val userId = remember { "user123" } // TODO: 可从登录信息获取用户ID
    var avatarUrl by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                viewModel.uploadAvatar(context, it, userId) { uploadedUrl ->
                    if (uploadedUrl != null) {
                        avatarUrl = uploadedUrl
                        Toast.makeText(context, "头像上传成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", fontSize = 20.sp) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF7F7F7),
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 🔽 头像部分
            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = avatarUrl ?: R.drawable.default_avatar,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .clickable { launcher.launch("image/*") }, // ✅ 打开文件选择器
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color.White, shape = CircleShape)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Avatar", tint = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text("user123", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("+852 4 635 9479", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = Color.LightGray)

            // 🔽 示例设置项
            SettingsItem(icon = Icons.Default.Person, title = "My Profile") { }
            SettingsItem(icon = painterResource(R.drawable.default_avatar), title = "Saved Messages") { }
            SettingsItem(icon = painterResource(R.drawable.default_avatar), title = "Recent Calls") { }
            SettingsItem(icon = painterResource(R.drawable.default_avatar), title = "Devices") { }
            SettingsItem(icon = painterResource(R.drawable.default_avatar), title = "Chat Folders") { }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Log Out",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { /* TODO: logout */ }
            )
        }
    }
}


@Composable
fun SettingsItem(
    icon: Any,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (icon) {
            is ImageVector -> Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
            is Painter -> Image(painter = icon, contentDescription = title, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 16.sp)
    }
}
