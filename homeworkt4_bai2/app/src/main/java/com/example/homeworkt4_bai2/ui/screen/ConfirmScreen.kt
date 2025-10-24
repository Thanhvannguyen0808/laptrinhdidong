package com.example.homeworkt4_bai2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeworkt4_bai2.R
import com.example.homeworkt4_bai2.ui.component.GradientButton

@Composable
fun ConfirmScreen(navController: NavController, email: String, code: String, password: String) {
    var passwordValue by remember { mutableStateOf(password) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(Modifier.height(40.dp))
        Image(painter = painterResource(id = R.drawable.logo_uth), contentDescription = null, modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(12.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(18.dp))
        Text("Confirm", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text("We are here to help you!", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(18.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            singleLine = true,
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = code,
            onValueChange = {},
            label = { Text("Code") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            singleLine = true,
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))
        GradientButton("Submit", modifier = Modifier.fillMaxWidth()) {
            navController.navigate("login") {
                popUpTo("forget") { inclusive = true }
            }
        }
    }
}
