package com.example.datastore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datastore.ui.screens.ThemeScreen
import com.example.datastore.viewmodel.ThemeViewModel

@Composable
fun AppNav(viewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "theme") {
        composable("theme") {
            ThemeScreen(viewModel) {
                // when apply pressed -> navigate to home and pop theme
                navController.navigate("home") {
                    popUpTo("theme") { inclusive = true }
                }
            }
        }


    }
}
