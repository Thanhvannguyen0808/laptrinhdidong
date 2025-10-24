package com.example.uthsmarttasks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uthsmarttasks.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("onboard") { OnBoardingScreen(navController) }
        composable("home") { HomeScreen() }
    }
}
