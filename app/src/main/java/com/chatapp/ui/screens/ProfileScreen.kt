package com.chatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userId: String,
    userName: String,
    avatarUrl: String?
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (avatarUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUrl),
                    contentDescription = "User Avatar",
                    modifier = Modifier.size(100.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Default Avatar",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "User ID: $userId", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Nickname: $userName", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
