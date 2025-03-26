package com.chatapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chatapp.R  // 假设有个 R.drawable.default_avatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendItem(
    userId: String,
    nickname: String,
    avatarUrl: String? = null,  // 如果后端提供头像URL，可传入；否则可为null
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        elevation = elevatedCardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // 左侧头像
            val shape: Shape = MaterialTheme.shapes.medium
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                if (!avatarUrl.isNullOrEmpty()) {
                    // Coil 异步加载头像
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "Avatar",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // 无头像URL时，使用占位图
                    Image(
                        painter = painterResource(id = R.drawable.default_avatar),
                        contentDescription = "Default Avatar",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 右侧文字区域
            Column {
                Text(
                    text = nickname,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "User ID: $userId",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
