package com.example.apk_demo.pages.todo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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


@Composable
fun TodoMain(nav: NavHostController, store: TodoViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TodoList(store.todoItems.observeAsState(listOf()), store)
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { store.addItem(TodoItemData(title = "测试测试测试")) }
        ) {
            Text("添加 ")
        }
    }
}

@Composable
fun TodoList(items: State<List<TodoItemData>>, store: TodoViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(),
        userScrollEnabled = true

    ){
        items(items.value) {it ->
            TodoItem(data = it, handleCheck = {id -> store.toggleItem(id) })
        }
    }
}

@Composable
fun TodoItem(data: TodoItemData, handleCheck: (id: UUID) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .border(1.dp, Color.LightGray)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(data.title, modifier = Modifier.padding(start = 10.dp))
        Checkbox(checked = data.status, onCheckedChange = { handleCheck(data.id) })
    }
}
