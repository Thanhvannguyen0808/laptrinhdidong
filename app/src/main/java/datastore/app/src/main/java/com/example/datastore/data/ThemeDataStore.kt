package com.example.datastore.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context._dataStore by preferencesDataStore("theme_pref")
val Context.dataStore get() = _dataStore

class ThemeDataStore(private val context: Context) {

    companion object {
        val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val currentTheme: Flow<String> =
        context.dataStore.data.map { pref ->
            pref[THEME_KEY] ?: "LIGHT"
        }

    suspend fun saveTheme(value: String) {
        context.dataStore.edit { pref ->
            pref[THEME_KEY] = value
        }
    }
}
