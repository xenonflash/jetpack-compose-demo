package com.example.apk_demo.pages.todo

import android.content.Context
import android.util.Log
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.apk_demo.dataStore.Storage
import java.util.UUID
class TodoViewModel : ViewModel() {
    var todoItems = mutableStateListOf<TodoItemData>()
        private set
    lateinit var storage: Storage

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
    fun initStorage(ctx: Context) {
        storage = Storage(ctx)
    }
}
val LocalStore = compositionLocalOf<TodoViewModel> { error("not init") }
