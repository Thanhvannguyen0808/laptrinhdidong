package com.example.uthsmarttasks.ui.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.uthsmarttasks.model.onBoardPages
import com.example.uthsmarttasks.ui.components.OnBoardContent

@Composable
fun OnBoardingScreen(navController: NavController) {
    var currentPage by remember { mutableStateOf(0) }
    val totalPages = onBoardPages.size

    OnBoardContent(
        page = onBoardPages[currentPage],
        currentPage = currentPage,
        totalPages = totalPages,
        onNextClick = {
            if (currentPage < totalPages - 1) {
                currentPage++
            } else {
                navController.navigate("home") {
                    popUpTo("onboard") { inclusive = true }
                }
            }
        },
        onBackClick = {
            if (currentPage > 0) currentPage--
        },
        onSkipClick = {
            navController.navigate("home") {
                popUpTo("onboard") { inclusive = true }
            }
        }
    )
}
