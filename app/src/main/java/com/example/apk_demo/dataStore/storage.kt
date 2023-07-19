package com.example.apk_demo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Storage(private val ctx: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeData")
        val TOKEN = stringPreferencesKey("token")
    }
    val getData: Flow<String?> = ctx.dataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    suspend fun saveData(value: String) {
        ctx.dataStore.edit { preferences ->
            preferences[TOKEN] = value
        }
    }
}
