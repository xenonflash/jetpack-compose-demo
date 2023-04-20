package com.example.apk_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apk_demo.ui.theme.ApkdemoTheme
import java.security.interfaces.DSAPublicKey
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
// remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApkdemoTheme {
                val data = listOf(
                    Msg(title = "测试1", content = "是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里"),
                    Msg(title = "测试2", content = "l路上开飞机就快乐男声")
                )
                Column() {
                    MsgCardList(items = data)
                    MoreComp()
                }
            }
        }
    }
}

data class Msg(val title: String, val content: String)

@Composable
fun MsgCard(msg: Msg) {
    // 状态变量
    var isExpanded by remember { mutableStateOf(false) }
    val bgColor by animateColorAsState(
        targetValue = if (isExpanded) Color(0xFFCCCC) else MaterialTheme.colorScheme.surface
    )

    // 处理点击
    fun handleClick() {
        isExpanded = !isExpanded
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 5.dp,
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clickable(onClick = { handleClick() }),

        color = bgColor
    ) {
        Row(
            modifier = Modifier.padding(all = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.ct6),
                contentDescription = "ct6",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(percent = 10))
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.secondaryContainer,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Column {
                Text(
                    msg.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    msg.content,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}


@Composable
fun MsgCardList(items: List<Msg>) {
    LazyColumn() {
        items(items) {msg ->
            MsgCard(msg)
        }
    }
}


@Composable
fun MoreComp() {

// 按钮
    var counter by remember { mutableStateOf(0) }

    Surface(
       shadowElevation = 5.dp,
       modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("按钮")
            Button(onClick = {}) {
                Icon(Icons.Filled.Favorite, contentDescription = "收藏", modifier = Modifier.size(10.dp))
                Text("图标按钮")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { counter++ }
            ) {
                Text(
                    text = "增加"
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { counter-- }
            ) {
                Text(
                    text = "减少"
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = counter.toString())
        }
    }

//    弹窗
    var openDialog by remember { mutableStateOf(false) }
    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("弹窗 AlertDialog")
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { openDialog = true }
            ) {
                Text(
                    text = "打开弹窗"
                )
            }
        }

        if (openDialog) AlertDialog(
            onDismissRequest = { openDialog = false },
            title = {
                Text("弹窗标题")
            },
            text = {
                Text("弹窗内容")
            },
            confirmButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("取消")
                }
            }
        )
    }

//    卡片
    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("卡片 Card")
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp) // 外边距
                    .clickable { },

                // 设置点击波纹效果，注意如果 CardDemo() 函数不在 MaterialTheme 下调用
                // 将无法显示波纹效果
            ) {
                Column(
                    modifier = Modifier.padding(15.dp) // 内边距
                ) {
                    Text(
                        buildAnnotatedString {
                            append("欢迎来到 ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                            ) {
                                append("Jetpack Compose 博物馆")
                            }
                        }
                    )
                    Text(
                        buildAnnotatedString {
                            append("你现在观看的章节是 ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                                append("Card")
                            }
                        }
                    )
                }
            }
        }
    }

//    浮动按钮
    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("浮动按钮 FloationActionButton")
            FloatingActionButton(
                onClick = { openDialog = true },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "浮动按钮")
            }
        }
    }
    
//    图片
    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("图片")
            Spacer(modifier = Modifier.absolutePadding(right = 10.dp))
            Image(
                painter = painterResource(id = R.drawable.ct6),
                contentScale = ContentScale.Crop,
                contentDescription = "ct6",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .shadow(10.dp, CircleShape)
//                    .border(border = BorderStroke(5.dp, Color.Blue), CircleShape)

            )

            Spacer(modifier = Modifier.padding(15.dp))
            Text(text = "网络图片：")
            AsyncImage(
                model = "https://cdn.pixabay.com/photo/2023/04/03/04/48/woman-7895953_1280.jpg",
                contentDescription = "image",
                modifier = Modifier.size(100.dp),
                placeholder = painterResource(id = R.drawable.ct6)
            )
        }
    }
}
