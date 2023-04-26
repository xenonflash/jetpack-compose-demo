package com.example.apk_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apk_demo.pages.LoginPage
import com.example.apk_demo.pages.MainPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable(route = "main", content = { MainPage(nav = navController) })
                composable(route = "login", content = { LoginPage(nav = navController) })
            }
        }
    }
}
