package com.example.apk_demo.pages

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.excludeFromSystemGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieDynamicProperties
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.apk_demo.R
import com.example.apk_demo.api.UserModel
import com.example.apk_demo.api.getUserInfo
import kotlinx.coroutines.delay

@Composable
fun HomePage(nav: NavController) {
    var showSplash by remember {
        mutableStateOf(true)
    }
    Box {
        LaunchedEffect(key1 = "hide splash screen", block = {
            delay(1500)
            showSplash = false
        })
            MainContent()
        AnimatedVisibility(
            visible = showSplash,
            exit = fadeOut()
        ) {
            SplashScreen()
        }
    }
}


@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }


}

@Composable
fun MainContent() {

    Column(Modifier.fillMaxSize()) {
        var checked by remember {
            mutableStateOf(false)
        }
        var userInfo by remember {
            mutableStateOf<UserModel>(UserModel(name = "", age = 0, avatar = ""))
        }

        var msg by remember {
            mutableStateOf("你好")
        }
        fun getUser() {
            getUserInfo { data -> userInfo = data as UserModel }
            msg = "我不好"
        }


        Button(onClick = { getUser() }) {
            Text("获取用户信息")
        }

        Text("Home", style = TextStyle(fontSize = 28.sp))
        Text("用户名: " + userInfo.name)
        Text("年龄: " + userInfo.age)
        Text("msg: $msg")
        AsyncImage(model = userInfo.avatar, contentDescription = "user avatar")
    }
}



@Composable
fun WebComp() {
    // Declare a string that contains a url
    val mUrl = "https://news.google.com"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            var layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}