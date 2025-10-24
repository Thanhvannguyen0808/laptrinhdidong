package com.example.homeworkt4_bai2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
fun ForgetPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo_uth), contentDescription = null, modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(12.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(18.dp))
        Text("Forgot Password?", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("Enter your registered email to receive verification code", style = MaterialTheme.typography.bodySmall, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(22.dp))
        GradientButton("Next", modifier = Modifier.fillMaxWidth()) {
            if (email.isNotEmpty()) {
                navController.navigate("verify/$email")
            } else {
                println("Email không được để trống")
            }
        }
    }
}
