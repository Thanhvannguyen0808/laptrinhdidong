package com.example.uthsmarttasks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(user?.photoUrl),
            contentDescription = "Avatar",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = user?.displayName ?: "",
            onValueChange = {},
            label = { Text("Name") },
            readOnly = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = user?.email ?: "",
            onValueChange = {},
            label = { Text("Email") },
            readOnly = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "23/05/1995",
            onValueChange = {},
            label = { Text("Date of Birth") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("login") }) {
            Text("Back")
        }
    }
}
