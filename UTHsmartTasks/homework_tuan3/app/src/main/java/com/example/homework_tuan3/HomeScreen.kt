package com.example.homework_tuan3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UI Components List",
                        color = Color(0xFF008CBA), // Màu xanh dương
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Display",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            ComponentCard("Text", "Displays text") {
                navController.navigate("text")
            }

            ComponentCard("Image", "Displays an image") {
                navController.navigate("image")
            }

            Text(
                text = "Input",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            ComponentCard("TextField", "Input field for text") {
                navController.navigate("textfield")
            }

            ComponentCard("PasswordField", "Input field for passwords") {
                navController.navigate("password")
            }

            Text(
                text = "Layout",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            ComponentCard("Column", "Arranges elements vertically") {
                navController.navigate("columnlayout")
            }

            ComponentCard("Row", "Arranges elements horizontally") {
                navController.navigate("rowlayout")
            }

        }
    }
}

// Component Card dùng lại được
@Composable
fun ComponentCard(title: String, description: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = Color(0xFFB3E5FC),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(description, fontSize = 13.sp)
        }
    }
}
