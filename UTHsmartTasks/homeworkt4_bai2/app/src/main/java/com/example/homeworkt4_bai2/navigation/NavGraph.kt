package com.example.homeworkt4_bai2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.homeworkt4_bai2.ui.screen.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "forget") {

        composable("forget") { ForgetPasswordScreen(navController) }

        composable(
            "verify/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController, email)
        }

        composable(
            "reset/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            val code = backStack.arguments?.getString("code") ?: ""
            ResetPasswordScreen(navController, email, code)
        }

        composable(
            "confirm/{email}/{code}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            val code = backStack.arguments?.getString("code") ?: ""
            val password = backStack.arguments?.getString("password") ?: ""
            ConfirmScreen(navController, email, code, password)
        }

        composable("login") { LoginScreen(navController) }
        composable("success") { SuccessScreen(navController) }
    }
}
