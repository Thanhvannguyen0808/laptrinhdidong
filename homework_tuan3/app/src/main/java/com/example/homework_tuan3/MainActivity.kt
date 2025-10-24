package com.example.homework_tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.homework_tuan3.ui.theme.Homework_tuan3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework_tuan3Theme {
                AppNavigation()
            }
        }
    }
}
