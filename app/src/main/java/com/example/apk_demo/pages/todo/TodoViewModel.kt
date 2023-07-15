package com.example.apk_demo.pages.todo

import android.util.Log
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class TodoViewModel : ViewModel() {
    var todoItems = mutableStateListOf<TodoItemData>()
        private set
    fun addItem(data: TodoItemData) {
        Log.d("-----", todoItems.toString())
        todoItems.add(data)

    }
    fun toggleItem(item: TodoItemData) {
        val idx = todoItems.indexOf(item)
        todoItems[idx] = item.copy(status = !item.status)
    }

    fun removeItem(item: TodoItemData) {
        todoItems.remove(item)
    }
}
val LocalStore = compositionLocalOf<TodoViewModel> { error("not init") }