package com.example.homework_tuan3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TextField") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Thông tin nhập") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (text.isEmpty()) "Tự động cập nhật dữ liệu theo textfield"
                else text,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
