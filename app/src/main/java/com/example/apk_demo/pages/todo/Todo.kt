package com.example.apk_demo.pages.todo

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.UUID



data class TodoItemData(
    var status: Boolean = false,
    val title: String = "",
    val content: String = "",
    val id: UUID = UUID.randomUUID(),
    val type: TodoType = TodoType.Misc,
    val deleted: Boolean = false
)

enum class TodoType {
    Event,
    Meeting,
    Daily,
    Birthday,
    Misc
}

val todoTypeIconMap = buildMap<TodoType, ImageVector> {
    put(TodoType.Misc, Icons.Filled.Home)
    put(TodoType.Meeting, Icons.Filled.Person)
    put(TodoType.Event, Icons.Filled.DateRange)
    put(TodoType.Daily, Icons.Filled.ShoppingCart)
    put(TodoType.Birthday, Icons.Filled.Star)
}


@Composable
fun TodoMain(nav: NavHostController) {
    val store = LocalStore.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TodoList(store.todoItems, store)
        NewItemInput(modifier = Modifier.align(Alignment.BottomCenter), handleAdd = { data -> store.addItem(data) })
    }
}

@Composable
fun TodoList(items: List<TodoItemData>, store: TodoViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(),
        userScrollEnabled = true

    ){
        items(items) {it ->
            TodoItem(data = it, handleCheck = store::toggleItem, handleRemove = store::removeItem)
        }
    }
}

@Composable
fun TodoItem(data: TodoItemData, handleCheck: (id: TodoItemData) -> Unit, handleRemove: (id: TodoItemData) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .border(1.dp, Color.LightGray)
            .fillMaxWidth()
    ) {
        Text(data.title, modifier = Modifier
            .padding(start = 10.dp)
            .weight(1f))
        Checkbox(checked = data.status, onCheckedChange = { handleCheck(data) })
        TextButton(onClick = { handleRemove(data) }) {
            Text(text = "❌")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NewItemInput(modifier: Modifier = Modifier, handleAdd: (data: TodoItemData) -> Unit) {
    val ctx = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf(TodoType.Misc)
    }

    fun handleChange(str: String) {
        text = str
    }
    @SuppressLint("ShowToast")
    fun handleItemAdd() {
        handleAdd(
            TodoItemData(
                status = false,
                id = UUID.randomUUID(),
                title = text,
                content = "",
                type = type
            )
        )
        text = ""
        Toast.makeText(ctx, "添加成功", Toast.LENGTH_SHORT).show()
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .then(modifier)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TodoType.values().forEach { it ->
                IconButton(onClick = { type = it }) {
                    Icon(
                        todoTypeIconMap[it]!!,
                        contentDescription = "star",
                        tint = if (type === it) Color.DarkGray else Color.LightGray
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(Color.LightGray)
            .fillMaxWidth())
        Row(modifier = Modifier
            .fillMaxWidth()
            .then(modifier), verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                placeholder = { Text("输入内容...") },
                onValueChange = { handleChange(it) },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                maxLines = 1,
                // 配置软键盘
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                // 收起键盘
                keyboardActions = KeyboardActions(onDone = {
                    handleItemAdd()
                    keyboardController?.hide()
                })
            )
            Button(onClick = {handleItemAdd()}, enabled = text.isNotBlank()) {
                Text(text = "添加")
            }
        }
    }

}
