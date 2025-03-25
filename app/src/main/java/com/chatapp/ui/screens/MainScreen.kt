package com.chatapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.chatapp.ui.nav.Screen

/**
 * MainScreen - 包含顶部标题、底部导航栏、以及基于NavHost的页面容器。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // NavHostController 用于管理底部导航的路由切换
    val mainScreenNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Main") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            NavigationBar {
                // 获取当前的路由信息，以确定哪一个 NavigationBarItem 被选中
                val navBackStackEntry by mainScreenNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // 底部导航的三个页面：Chat、FriendList、Settings
                val screens = listOf(Screen.Chat, Screen.FriendList, Screen.Settings)
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            // 在此放置对应的图标
                            // Icon(Icons.Default.XXX, contentDescription = screen.route)
                        },
                        label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                        selected = (currentRoute == screen.route),
                        onClick = {
                            // 使用 mainScreenNavController 进行页面跳转
                            mainScreenNavController.navigate(screen.route) {
                                // 在栈顶已是同一路由时，避免重复创建
                                launchSingleTop = true
                                // 切换到新页面时，弹出回到图中的起始页，以清空堆栈
                                popUpTo(mainScreenNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // 当我们返回时，保持之前保存的状态 (如滚动位置)
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // 在 Scaffold 内容区域设置一个 NavHost，以容纳不同页面
        NavHost(
            navController = mainScreenNavController,
            startDestination = Screen.Chat.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Chat.route) {
                // 替换为您的聊天界面实现
                ChatScreen(
                    messages = emptyList(),
                    onSendMessage = { /* TODO: 实现发送消息逻辑 */ }
                )
            }
            composable(Screen.FriendList.route) {
                // 替换为您的好友列表界面实现
                FriendListScreen(friends = emptyList())
            }
            composable(Screen.Settings.route) {
                // 替换为您的设置界面实现
                SettingsScreen()
            }
        }
    }
}
