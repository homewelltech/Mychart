package com.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chatapp.ui.screens.*
import com.chatapp.viewmodel.UserViewModel

@Composable
fun ChatAppNavGraph(navController: NavHostController) {
    val viewModel: UserViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, viewModel)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
        composable("chats") {
            ChatListScreen(navController)
        }
        composable("contacts") {
            ContactListScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("chat/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: return@composable
            ChatScreen(navController, userId)
        }
    }
}
