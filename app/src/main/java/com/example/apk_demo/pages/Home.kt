package com.example.apk_demo.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomePage(nav: NavController) {
    Surface() {
        Text("Home")
        Button(onClick = { nav.navigate("login") }) {
            Text("跳转回登陆")
        }
    }
}

