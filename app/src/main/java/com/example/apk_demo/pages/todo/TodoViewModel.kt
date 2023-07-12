package com.example.apk_demo.pages.todo

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class TodoViewModel : ViewModel() {
    var todoItems = mutableStateListOf<TodoItemData>()
        private set
    fun addItem(data: TodoItemData) {
        Log.d("-----", todoItems.toString())
        todoItems.add(data)

    }
    fun toggleItem(id: UUID) {
        Log.d("-----", id.toString())
        todoItems = todoItems.onEach {
            if (it.id === id) {
                Log.d("-----", "toggle!")
                it.status = !it.status
            }
        }
    }

    fun removeItem(id: UUID) {
        todoItems.removeIf { it -> it.id == id }
    }
}
