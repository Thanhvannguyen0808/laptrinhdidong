package com.example.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastore.data.ThemeDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: ThemeDataStore) : ViewModel() {

    // stateIn -> StateFlow so Compose can collectAsState()
    val theme = pref.currentTheme.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "LIGHT"
    )

    fun saveTheme(value: String) {
        viewModelScope.launch {
            pref.saveTheme(value)
        }
    }
}
