package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.navigation.NavGraph
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
