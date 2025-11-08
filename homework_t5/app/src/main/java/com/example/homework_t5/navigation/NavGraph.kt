package com.example.homework_t5.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homework_t5.ui.screens.LoginScreen
import com.example.homework_t5.ui.screens.TaskDetailScreen
import com.example.homework_t5.ui.screens.TaskListScreen
import com.example.homework_t5.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth

object Routes {
    const val LOGIN = "login"
    const val LIST = "list"
    const val DETAIL = "detail"
    const val ADD = "add" // chuẩn bị sẵn cho nút Add
}

@Composable
fun AppNavGraph(vm: TaskViewModel) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    // Nếu người dùng đã đăng nhập → vào danh sách task
    val startDestination = if (auth.currentUser != null) Routes.LIST else Routes.LOGIN

    NavHost(navController = navController, startDestination = startDestination) {

        // 📌 Màn hình đăng nhập
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.LIST) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // 📋 Màn hình danh sách task
        composable(Routes.LIST) {
            // Nếu người dùng bị logout trong khi ở list → tự quay lại login
            LaunchedEffect(Unit) {
                if (auth.currentUser == null) {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LIST) { inclusive = true }
                    }
                }
            }

            TaskListScreen(
                viewModel = vm,
                onOpenDetail = { id ->
                    navController.navigate("${Routes.DETAIL}/$id")
                },
                onLogout = {
                    auth.signOut()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LIST) { inclusive = true }
                    }
                },
                onAddTask = {
                    // 🟢 Khi nhấn nút +, tạm thời hiển thị log hoặc toast
                    // Sau này có thể thêm màn hình tạo task riêng
                    println("Add Task Clicked!")
                }
            )
        }

        // 📄 Màn hình chi tiết task
        composable(
            route = "${Routes.DETAIL}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(
                taskId = id,
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
