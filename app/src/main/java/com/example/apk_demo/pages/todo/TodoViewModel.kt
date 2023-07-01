package com.example.apk_demo.pages.todo

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class TodoViewModel : ViewModel() {
    private val _todoItems = MutableLiveData(listOf<TodoItemData>())
    val todoItems = _todoItems
    fun addItem(data: TodoItemData) {
        Log.d("-----", _todoItems.value.toString())
        _todoItems.value = _todoItems.value!! + listOf(data)

    }
    fun toggleItem(id: UUID) {
        Log.d("-----", id.toString())
        _todoItems.value = _todoItems.value!!.onEach {

            if (it.id === id) {
                Log.d("-----", "toggle!")
                it.status = !it.status
            }
        }
    }

    fun removeItem(id: UUID) {
        _todoItems.value = todoItems.value?.filter { it -> it.id !== id }
    }
}
