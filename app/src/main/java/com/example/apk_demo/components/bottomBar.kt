package com.example.apk_demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val items = listOf<String>("Home", "Dev")
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach{
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(Icons.Filled.Home, contentDescription = it)
                    Text(text = it)
                }
            }
        }
    }
}