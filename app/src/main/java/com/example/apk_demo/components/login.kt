package com.example.apk_demo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// login components

@Composable
fun LoginForm() {
    Surface(modifier = Modifier.border(BorderStroke(1.dp, Color.Gray)).fillMaxWidth().height(100.dp)) {
        Text("login form")
    }
}