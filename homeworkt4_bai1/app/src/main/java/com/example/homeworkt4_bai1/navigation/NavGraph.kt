package com.example.homeworkt4_bai1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.homeworkt4_bai1.ui.screen.BookListScreen
import com.example.homeworkt4_bai1.ui.screen.ManageScreen
import com.example.homeworkt4_bai1.ui.screen.StudentScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier // 🩵 thêm dòng này
) {
    NavHost(
        navController = navController,
        startDestination = "manage",
        modifier = modifier // 🩵 truyền modifier vào đây
    ) {
        composable("manage") { ManageScreen() }
        composable("books") { BookListScreen() }
        composable("students") { StudentScreen() }
    }
}
