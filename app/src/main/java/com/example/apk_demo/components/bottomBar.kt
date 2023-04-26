package com.example.apk_demo.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar() {
    val items = listOf<String>("Home", "Dev")
    Row(
        modifier = Modifier.border(1.dp, Color.Red).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach{
            Column() {
                Icon(Icons.Filled.Face, contentDescription = it)
                Text(text = it)
            }
        }
    }
}