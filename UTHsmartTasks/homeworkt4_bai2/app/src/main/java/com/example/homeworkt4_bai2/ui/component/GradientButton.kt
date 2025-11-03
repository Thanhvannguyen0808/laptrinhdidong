package com.example.homeworkt4_bai2.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // 👈 để onClick ở CUỐI
) {
    var pressed by remember { mutableStateOf(false) }

    val gradient = Brush.horizontalGradient(
        colors = if (pressed)
            listOf(Color(0xFF00A2C7), Color(0xFF00789E))
        else
            listOf(Color(0xFF00B4DB), Color(0xFF0083B0))
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (pressed) Color.LightGray else Color.White,
        label = "Button Text Animation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = gradient,
                shape = RoundedCornerShape(30.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                pressed = true
                onClick()
                pressed = false
            }
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = animatedTextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
