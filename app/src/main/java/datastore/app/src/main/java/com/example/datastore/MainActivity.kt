package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.datastore.data.ThemeDataStore
import com.example.datastore.ui.navigation.AppNav
import com.example.datastore.ui.theme.AppTheme
import com.example.datastore.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = ThemeDataStore(this)
        val viewModel = ThemeViewModel(pref)

        setContent {
            val theme by viewModel.theme.collectAsState()

            AppTheme(theme) {
                AppNav(viewModel)
            }
        }
    }
}
