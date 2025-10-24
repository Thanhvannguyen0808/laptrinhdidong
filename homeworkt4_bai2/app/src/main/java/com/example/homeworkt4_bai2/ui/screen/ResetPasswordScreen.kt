package com.example.homeworkt4_bai2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeworkt4_bai2.R
import com.example.homeworkt4_bai2.ui.component.GradientButton
import com.example.homeworkt4_bai2.ui.component.PasswordTextField

@Composable
fun ResetPasswordScreen(navController: NavController, email: String, code: String) {
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo_uth), contentDescription = null, modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(12.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(18.dp))
        Text("Create New Password", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("Your new password must be different from previously used password", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(16.dp))

        PasswordTextField(value = password, onValueChange = { password = it }, label = "Password")
        Spacer(Modifier.height(8.dp))
        PasswordTextField(value = confirm, onValueChange = { confirm = it }, label = "Confirm Password")
        Spacer(Modifier.height(22.dp))

        GradientButton("Next") {
            if (password.isNotEmpty() && password == confirm) {
                navController.navigate("confirm/$email/$code/$password")
            } else {
                println("Password và Confirm không khớp hoặc rỗng")
            }
        }
    }
}
