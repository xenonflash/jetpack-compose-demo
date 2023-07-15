package com.example.apk_demo

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apk_demo.pages.LoginPage
import com.example.apk_demo.pages.DevPage
import com.example.apk_demo.pages.HomePage
import com.example.apk_demo.pages.todo.LocalStore
import com.example.apk_demo.pages.todo.TodoMain
import com.example.apk_demo.pages.todo.TodoViewModel
import java.util.UUID

class MainActivity : ComponentActivity() {
    // viewmodel
    private val todoViewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Utils.LifecycleEffect(
                onCreate = {
                    Log.d("", "onCreated")
                }
            )
            val navController = rememberNavController()
            CompositionLocalProvider(LocalStore provides todoViewModel) {
                NavHost(navController = navController, startDestination = "login") {
                    composable(route = "dev", content = { DevPage(nav = navController) })
                    composable(route = "todo", content = { TodoMain(nav = navController ) })
                    composable(route = "login", content = { LoginPage(nav = navController) })
                    composable(route = "home", content = { HomePage(nav = navController) })
                }
            }
        }
    }

}
