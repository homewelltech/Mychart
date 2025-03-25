package com.chatapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MessageItem(message: String, isOwnMessage: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isOwnMessage) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .widthIn(max = 250.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(
                containerColor = if (isOwnMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = if (isOwnMessage) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
