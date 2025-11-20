package com.example.composelayout

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePage() {
    var followed by remember { mutableStateOf(false) }
    var followerCount by remember { mutableStateOf(120) }
    val scale by animateFloatAsState(if (followed) 1.05f else 1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F8FF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Box(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF64B5F6)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color(0xFF64B5F6),
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        // Card thông tin
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nguyễn Thị Thanh Vân", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("IT Student - University of Transport", color = Color.Gray)
                Spacer(Modifier.height(12.dp))
                Text("Followers: $followerCount", color = Color(0xFF1976D2))

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            followed = !followed
                            followerCount += if (followed) 1 else -1
                        },
                        modifier = Modifier.scale(scale),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (followed) Color.Gray else Color(0xFF1976D2)
                        )
                    ) {
                        Text(if (followed) "Following" else "Follow")
                    }

                    OutlinedButton(onClick = {}) {
                        Text("Message")
                    }
                }
            }
        }

        Text(
            "Compose Layout Showcase",
            textAlign = TextAlign.Center,
            color = Color(0xFF2196F3),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
