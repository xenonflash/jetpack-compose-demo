package com.example.apk_demo.pages

import UserInfo
import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.apk_demo.R
import com.example.apk_demo.components.BottomBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import userApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomePage(nav: NavController) {
    var showSplash by remember {
        mutableStateOf(true)
    }
    var pagerState = rememberPagerState()

    val coroutinScope = rememberCoroutineScope()

    LaunchedEffect(key1 = "hide splash screen", block = {
        delay(700)
        showSplash = false
    })

    Box() {
        Scaffold(
            topBar = {
                 TopAppBar(
                     title = {
                         Text(text = "Todo List")
                     },
                     navigationIcon = {
                         Icon(Icons.Filled.Menu, contentDescription = "menu", modifier = Modifier.padding(end = 10.dp), tint = MaterialTheme.colorScheme.onPrimary)
                     },
                     colors = TopAppBarDefaults.mediumTopAppBarColors(
                         containerColor = MaterialTheme.colorScheme.primary,
                         titleContentColor = MaterialTheme.colorScheme.onPrimary
                     )
                 )
            },
            bottomBar = {
                BottomBar(activeIdx = pagerState.currentPage, onClick = {
                    coroutinScope.launch { pagerState.animateScrollToPage(it) }
                })
            }
        ) {
            HorizontalPager(pageCount = 3, state = pagerState, modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxSize()) {
                when(it) {
                    0 -> BodyContent()
                    1 -> Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("page2")
                    }
                }
            }
        }

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
fun BodyContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ){
        var checked by remember {
            mutableStateOf(false)
        }
        var userInfo by remember {
            mutableStateOf<UserInfo>(UserInfo())
        }

        var msg by remember {
            mutableStateOf("你好")
        }
        val coroutineScope = rememberCoroutineScope()

        fun getUser() {
            coroutineScope.launch {
                userInfo = userApi.getUserInfo()
            }
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