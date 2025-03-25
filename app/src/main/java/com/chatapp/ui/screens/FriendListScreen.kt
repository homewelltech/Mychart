package com.chatapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chatapp.ui.components.FriendItem

@Composable
fun FriendListScreen(
    friends: List<Pair<String, String>>,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                Text(text = errorMessage, modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                LazyColumn(contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)) {
                    items(friends) { friend ->
                        FriendItem(userId = friend.first, nickname = friend.second)
                    }
                }
            }
        }
    }
}
