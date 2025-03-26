package com.chatapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chatapp.ui.screens.LoginScreen
import com.chatapp.ui.screens.MainScreen
import com.chatapp.ui.screens.RegisterScreen
import com.chatapp.ui.screens.ChatScreen  // 独立的聊天页

@Composable
fun ChatAppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // 1) 登录页面
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // 登录成功后跳转到 Main
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = {
                    // 点击“注册”，跳转到 Register 路由
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // 2) 注册页面 (可选)
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // 注册成功后回到 Login
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // 3) 主界面
        composable(Screen.Main.route) {
            // 将顶层 navController 传递给 MainScreen
            // 以便从内部列表点击时，能跳转到 Chat (顶层路由)
            MainScreen(navController = navController)
        }

        // 4) 聊天详情 (独立在顶层，不在 MainScreen 内部)
        // 可以使用 query 参数或 path param
        composable(route = Screen.Chat.route + "?conversationId={conversationId}&isGroup={isGroup}&nikeName={nikeName}") {
            val conversationId = it.arguments?.getString("conversationId") ?: ""
            val isGroup = it.arguments?.getBoolean("isGroup") ?: false
            val nikeName=it.arguments?.getString("nikeName")

            ChatScreen(
                conversationId = conversationId,
                nikeName=nikeName,
                isGroup = isGroup
            )
        }
    }
}
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")

    // 在顶层声明 Chat
    object Chat : Screen("chat")

    // 下述几个主要是 MainScreen 内部的目的地
    object MessageList : Screen("messageList")
    object FriendList : Screen("friendList")
    object Settings : Screen("settings")



}
