package com.example.homework_tuan3

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework_tuan3.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }

        // --- Display ---
        composable("text") { TextDetailScreen(navController) }
        composable("image") { ImageScreen(navController) }

        // --- Input ---
        composable("textfield") { TextFieldScreen(navController) }
        composable("password") { PasswordFieldScreen(navController) }

        // --- Layout ---
        composable("rowlayout") { RowLayoutScreen(navController) }
        composable("columnlayout") { ColumnLayoutScreen(navController) } // ✅ Thêm dòng này
    }
}
