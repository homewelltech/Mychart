package com.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.chatapp.ui.nav.ChatAppNavGraph
import com.chatapp.ui.screens.LoginScreen
import com.chatapp.ui.theme.ChatAppTheme
import com.chatapp.utils.LoginPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    LoginPreference.init(this)
                    ChatAppNavGraph(navController = navController)
                }
            }
        }
    }
}