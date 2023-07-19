package com.example.apk_demo.pages

import UserInfo
import android.annotation.SuppressLint
import android.util.Log
import android.util.Size
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.animateRectAsState
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
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
import kotlin.math.roundToInt

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
                .padding(top = 70.dp)
                .fillMaxSize()) {
                when(it) {
                    0 -> BodyContent()
                    1 -> Animations()
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
    var userInfo by remember {
        mutableStateOf<UserInfo>(UserInfo())
    }
    var msg by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()

    fun getUser() {
        coroutineScope.launch {
            userInfo = userApi.getUserInfo()
            Log.d("----------", userInfo.avatar)
        }
    }
    val ctx = LocalContext.current
    var dragOffsetX by remember {
        mutableStateOf(0f)
    }
    var dragOffsetY by remember {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ){
        Button(onClick = { getUser() }) {
            Text("获取用户信息")
        }

        Text("用户名: " + userInfo.name)
        Text("年龄: " + userInfo.age)
//        AsyncImage(model = userInfo.avatar, contentDescription = "user avatar")
        Surface(
            modifier = Modifier
                .size(80.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { tapOffset ->
                            Toast
                                .makeText(ctx, "拍一拍我干嘛", Toast.LENGTH_SHORT)
                                .show()
                            msg += "小黄鸭拍了拍我\n"
                        }
                    )
                }
                .offset{ IntOffset(dragOffsetX.roundToInt(), dragOffsetY.roundToInt()) }
//                .draggable(
//                    orientation = Orientation.Horizontal,
//                    state = rememberDraggableState(onDelta = {
//                        dragOffsetX += it
//                    })
//                ),
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        dragOffsetX += dragAmount.x
                        dragOffsetY += dragAmount.y
                    }
                },
            shape = CircleShape, shadowElevation = 10.dp,

        ) {
            AsyncImage(model = "https://www.getillustrations.com/photos/pack/video/55895-3D-AVATAR-ANIMATION.gif", contentDescription = "default_avatar")
        }
        Text(text = msg)
    }
}


@Composable
fun Animations() {
    var checked by remember {
        mutableStateOf(false)
    }

    val animXOffset by animateDpAsState(targetValue = if (checked) 100.dp else 0.dp)
    val animRadius by animateDpAsState(targetValue = if (checked) 20.dp else 50.dp)
    val animColor by animateColorAsState(targetValue = if (checked) Color.Green else Color.Red)
    Column(
        Modifier
            .fillMaxSize()
            .border(1.dp, Color.Red)
            .padding(bottom = 80.dp)) {
        Text(text = "animation", style = MaterialTheme.typography.titleMedium)

        // movable
        Surface(
            Modifier
                .size(100.dp)
                .offset(x = animXOffset)
                .clickable { checked = !checked },
            shape =RoundedCornerShape(animRadius),
            color = animColor,
            shadowElevation = 10.dp
        ){
            Text("点击")
        }

    }
}
