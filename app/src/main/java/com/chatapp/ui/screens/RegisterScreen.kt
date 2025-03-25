package com.chatapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatapp.model.UiState
import com.chatapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit = {}
) {
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val registerState = userViewModel.registerState.value
    val snackbarHostState = remember { SnackbarHostState() }

    // 背景渐变
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.secondaryContainer)
    )

    // 监听注册状态变化，执行成功或错误处理
    LaunchedEffect(registerState) {
        when (registerState) {
            is UiState.Success -> {
                if (registerState.data) {
                    onRegisterSuccess()
                }
            }
            is UiState.Error -> {
                snackbarHostState.showSnackbar(registerState.message)
            }
            else -> { /* Loading 状态或初始状态 */ }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Register") }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Your Account",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text("Nickname") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    colors = outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )
                Button(
                    onClick = {
                        userViewModel.register(nickname, password)
                    },
                    enabled = nickname.isNotBlank() && password.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (registerState is UiState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text("Register")
                    }
                }
            }
        }
    }
}
