package com.example.homework_tuan3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ComponentsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("UI Components List", style = MaterialTheme.typography.titleLarge)

        ComponentItem("Text", "Displays text") {
            navController.navigate("text_detail")
        }
        ComponentItem("Image", "Displays an image") {}
        ComponentItem("TextField", "Input field for text") {}
        ComponentItem("PasswordField", "Input field for passwords") {}
        ComponentItem("Column", "Arranges elements vertically") {}
        ComponentItem("Row", "Arranges elements horizontally") {}
    }
}

@Composable
fun ComponentItem(title: String, desc: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(desc, style = MaterialTheme.typography.bodySmall)
        }
    }
}