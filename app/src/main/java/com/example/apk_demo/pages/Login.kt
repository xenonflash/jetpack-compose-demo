package com.example.apk_demo.pages


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apk_demo.api.LoginMethod
import com.example.apk_demo.api.LoginReqModel
import com.example.apk_demo.api.UserApi
import okhttp3.internal.wait


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(nav: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            val ctx = LocalContext.current
            fun handleLogin() {
                val params = LoginReqModel(
                    loginMethod = LoginMethod.valueOf(""),
                    payload = object {
                        val username = username
                        var password = password
                    }
                )
                UserApi.login(params, onSuccess = {
                    Toast.makeText(ctx, "登陆成功", Toast.LENGTH_SHORT).show()
                    nav.navigate("home")
                })
            }


            Text(text = "登录", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(bottom = 15.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "用户名")},
                modifier = Modifier.padding(bottom = 15.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "密码") },
                modifier = Modifier.padding(bottom = 25.dp)
            )

            Button(onClick = { handleLogin() },
                Modifier
                    .width(200.dp)
                    .padding(end = 10.dp)) {
                Text(text = "登录")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text("注册")
            }
            Dev(nav = nav)
        }
    }
}


@Composable
fun Dev(nav: NavController) {
    Row() {
        TextButton(onClick = { nav.navigate("home") }) {
            Text(text = "home")
        }
        TextButton(onClick = { nav.navigate("dev") }) {
            Text(text = "dev")
        }
    }
}