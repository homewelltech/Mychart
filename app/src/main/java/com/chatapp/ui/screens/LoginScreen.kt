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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chatapp.model.UiState
import com.chatapp.viewmodel.UserViewModel
import com.chatapp.utils.LoginPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    // 尝试自动填充上次登录的用户信息
    var userId by remember { mutableStateOf(LoginPreference.getUserId(context) ?: "") }
    var password by remember { mutableStateOf(LoginPreference.getPassword(context) ?: "") }

    val loginState = userViewModel.loginState.value
    var token=""
    val snackbarHostState = remember { SnackbarHostState() }

    // 渐变背景
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
    )

    // 根据登录状态执行相应操作
    LaunchedEffect(loginState) {
        when (loginState) {
            is UiState.Success -> {
                if (loginState.data) {
                    // 登录成功后保存用户信息
                    LoginPreference.saveLoginInfo(context, userId, password)
                    onLoginSuccess()
                }
            }
            is UiState.Error -> {
                snackbarHostState.showSnackbar(loginState.message)
            }
            else -> { /* Loading 或初始状态 */ }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome Back") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
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
                    text = "ChatApp Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text("User ID") },
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
                    onClick = { userViewModel.login(userId, password) },
                    enabled = userId.isNotBlank() && password.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (loginState is UiState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Login")
                    }
                }
                // 添加注册按钮，点击时触发 onRegisterClick 回调
                Button(
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    }
}