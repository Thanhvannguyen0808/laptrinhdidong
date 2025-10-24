package com.example.homework_tuan3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ColumnLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Column Layout") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(width = 200.dp, height = 60.dp)
                        .background(Color(0xFFA5D6A7))
                )
            }
        }
    }
}
