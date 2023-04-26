package com.example.apk_demo.pages

import com.example.apk_demo.R
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
// remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.apk_demo.pages.LoginPage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun DevPage(nav: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Button(onClick = { nav.navigate("login")}) {
            Text(text = "跳转到登陆")
        }
        MsgCardList()
        MoreComp()

        val items = listOf<String>("Home", "Dev")
        Row(
            modifier = Modifier.border(1.dp, Color.Red).fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            items.forEach{
                Column(
                    Modifier.border(1.dp, Color.Blue)
                ) {
                    Icon(Icons.Filled.Face, contentDescription = it)
                    Text(text = it)
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
fun MsgCardList() {
    val data = listOf(
        Msg(title = "测试1", content = "是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里"),
        Msg(title = "测试2", content = "l路上开飞机就快乐男声"),
        Msg(title = "测试1", content = "是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里"),
    )
    LazyColumn(
        modifier = Modifier.height(300.dp)
    ) {
        items(data) {msg ->
            MsgCard(msg)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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

    //  滑动选择器
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
            var value by remember { mutableStateOf(0f)}
            Text("Slider")
            Spacer(modifier = Modifier.absolutePadding(right = 10.dp))
            Slider(
                value = value,
                onValueChange = { value = it},
                modifier = Modifier
                    .width(200.dp)
                    .padding(end = 10.dp),
                valueRange = 1f..10f,
                steps = 5
            )
            Text(value.toInt().toString())
        }
    }

    //  文本
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
            Text("文本",  modifier = Modifier.padding(end = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Blue))
                    .padding(10.dp)
            ) {
                Text("文本居左", modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Left))
                Text("文本居中", modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center))
                Text("文本居右", modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Right))
                Text("窗前明月光\n疑是地上霜\n举头望明月\n低头思故乡", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.kaishu)),
                    color = Color.Magenta
                ))
                val ctx = LocalContext.current
                Text("点击文本", modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(ctx, "你点击了文本", Toast.LENGTH_SHORT)
                                .show()
                        },
                    )
                )
                SelectionContainer() {
                    Text("可复制文本， 这段文本是可以复制的")
                }

                // 富文本
                Text(
                    buildAnnotatedString {
                        append( "这是")
                        withStyle(SpanStyle(background = MaterialTheme.colorScheme.secondaryContainer)) {
                            append("一段")
                        }
                        withStyle(SpanStyle(fontSize = 30.sp, color = Color.Red)) {
                            append("富文本")
                        }
                    }
                )

            }
        }
    }

    //  输入框
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
            var text by remember {
                mutableStateOf("")
            }
            Text("输入框", modifier = Modifier.absolutePadding(right = 10.dp))
            BasicTextField(
                value = text,
                onValueChange = { text = it },

                decorationBox = {innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(30.dp)
                            .width(200.dp)
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                                RoundedCornerShape(7.dp)
                            )

                    ) {
                        Icon(Icons.Filled.Face, contentDescription = null, Modifier.absolutePadding(right = 10.dp))
                        innerTextField()
                        Icon(Icons.Filled.Info, contentDescription = "icon", Modifier.absolutePadding(left = 10.dp))
                    }
                }
            )
            var text2 by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = text2,
                singleLine = true,
                modifier = Modifier.padding(5.dp),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { text2 = it },
                label = { Text(text = "用户名") }
            )
        }
    }
}
