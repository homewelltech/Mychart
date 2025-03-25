package com.chatapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * AddFriendDialog - 一个用来添加好友的对话框。
 *
 * @param onDismiss 对话框取消或点击外部区域时回调，用于关闭对话框。
 * @param onAdd 用户填写完好友ID并点击“确认”时的回调，传回输入的 friendId。
 * @param title Dialog 标题，默认为 "Add Friend"
 * @param confirmButtonLabel 确认按钮文案，默认为 "Add"
 * @param dismissButtonLabel 取消按钮文案，默认为 "Cancel"
 */
@Composable
fun AddFriendDialog(
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit,
    title: String = "Add Friend",
    confirmButtonLabel: String = "Add",
    dismissButtonLabel: String = "Cancel"
) {
    var friendId by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            // 可根据需要在这里加说明文字、提示等
            Column {
                OutlinedTextField(
                    value = friendId,
                    onValueChange = {
                        friendId = it
                        if (showError && it.isNotBlank()) {
                            // 如果用户曾经触发过错误，且现在输入不为空时隐藏错误
                            showError = false
                        }
                    },
                    label = { Text("Friend ID") },
                    singleLine = true, // 控制单行输入
                    isError = showError
                )
                if (showError) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Friend ID cannot be empty",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (friendId.isBlank()) {
                        showError = true
                    } else {
                        onAdd(friendId.trim())
                    }
                }
            ) {
                Text(confirmButtonLabel)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissButtonLabel)
            }
        }
    )
}
