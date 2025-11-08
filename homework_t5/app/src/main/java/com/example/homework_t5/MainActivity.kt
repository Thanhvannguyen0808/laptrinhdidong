package com.example.homework_t5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.homework_t5.navigation.AppNavGraph
import com.example.homework_t5.ui.theme.Homework_t5Theme
import com.example.homework_t5.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    private val vm by lazy { TaskViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework_t5Theme {
                AppNavGraph(vm = vm)
            }
        }
    }
}
