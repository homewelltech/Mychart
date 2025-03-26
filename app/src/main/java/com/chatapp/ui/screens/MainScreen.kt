package com.chatapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.chatapp.ui.nav.Screen

/**
 * MainScreen - 登录后进入的主界面，含底部导航。
 * 底部导航里管理：MessageList、FriendList、Settings。
 * Chat 页面不在此NavHost，而在顶层NavGraph里声明。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController  // 传入顶层 NavController，以便在列表内点击后跳转到聊天
) {
    // 内部 NavController，用于管理底部导航项 (messageList/friendList/settings)
    val mainScreenNavController = rememberNavController()

    // 底部导航的三个页面
    val bottomScreens = listOf(
        Screen.MessageList,
        Screen.FriendList,
        Screen.Settings
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Main") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by mainScreenNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomScreens.forEach { screen ->
                    NavigationBarItem(
                        icon = { /* 可放置图标 */ },
                        label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                        selected = (currentRoute == screen.route),
                        onClick = {
                            // 切换Tab
                            mainScreenNavController.navigate(screen.route) {
                                launchSingleTop = true
                                popUpTo(mainScreenNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // 内部 NavHost：负责切换"MessageList", "FriendList", "Settings"
        NavHost(
            navController = mainScreenNavController,
            startDestination = Screen.MessageList.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.MessageList.route) {
                // 消息列表
                // 点击列表项后要进入Chat页面 → 使用外层 navController
                MessageListScreen(
                    navController=navController
                )
            }
            composable(Screen.FriendList.route) {
                // 好友列表
                FriendListScreen(
                    navController=navController

                )
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
