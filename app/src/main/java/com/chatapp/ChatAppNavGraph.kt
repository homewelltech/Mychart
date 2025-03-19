// ChatAppNavGraph.kt (导航管理)
package com.chatapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chatapp.ui.screens.LoginScreen
import com.chatapp.ui.screens.RegisterScreen
import com.chatapp.ui.screens.ChatScreen

@Composable
fun ChatAppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("chat") { ChatScreen(navController) }
    }
}
