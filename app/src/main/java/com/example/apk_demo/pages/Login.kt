package com.example.apk_demo.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginPage(nav: NavController) {
    Surface(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Red))
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(text = "登录页面", style = TextStyle(fontSize = 20.sp))
        }

    }
}