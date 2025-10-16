package com.example.homework_tuan3.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("UI Components List") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { navController.navigate("text") }, modifier = Modifier.fillMaxWidth()) {
                Text("Text - Displays text")
            }
            Button(onClick = { navController.navigate("image") }, modifier = Modifier.fillMaxWidth()) {
                Text("Image - Displays an image")
            }
            Button(onClick = { navController.navigate("textfield") }, modifier = Modifier.fillMaxWidth()) {
                Text("TextField - Input field for text")
            }
            Button(onClick = { navController.navigate("password") }, modifier = Modifier.fillMaxWidth()) {
                Text("PasswordField - Input field for passwords")
            }
            Button(onClick = { navController.navigate("rowlayout") }, modifier = Modifier.fillMaxWidth()) {
                Text("Row - Arranges elements horizontally")
            }
        }
    }
}
