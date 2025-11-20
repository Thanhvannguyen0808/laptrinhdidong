package com.example.composelayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxDemo() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        Text("Top", Modifier.align(Alignment.TopStart), fontSize = 18.sp)
        Text("Center", Modifier.align(Alignment.Center), fontSize = 18.sp)
        Text("Bottom", Modifier.align(Alignment.BottomEnd), fontSize = 18.sp)
    }
}
