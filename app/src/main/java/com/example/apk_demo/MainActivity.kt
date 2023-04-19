package com.example.apk_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
// remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApkdemoTheme {
//                MsgCard(Msg("你好", "content"))
                val data = listOf(
                    Msg(title = "测试1", content = "是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里是开飞机离开撒娇弗兰克史蒂史蒂夫开始倒计时六块腹肌轮廓设计李静夫空间里看见了看师傅交流空间里"),
                    Msg(title = "测试2", content = "l路上开飞机就快乐男声"),
                    Msg(title = "测试3", content = "，美女，史蒂夫技术开发和健康"),
                    Msg(title = "测试4", content = "可撒娇疯狂i素交流空间"),
                    Msg(title = "测试5", content = "啊太晚而突然因为他一人"),
                    Msg(title = "测试6", content = "没吃呢美女吧，试飞"),
                    Msg(title = "测试7", content = "破我诶人陪我诶同款陌生地方。")
                )
                MsgCardList(items = data)
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
