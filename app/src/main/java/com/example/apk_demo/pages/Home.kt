package com.example.apk_demo.pages

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieDynamicProperties
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.apk_demo.R
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
        if (showSplash) SplashScreen() else Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text("Home")
            Button(onClick = { nav.navigate("login") }) {
                Text("跳转回登陆")
            }
            WebComp()
        }
    }
}


@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
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