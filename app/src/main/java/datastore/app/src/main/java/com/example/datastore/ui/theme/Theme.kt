package com.example.datastore.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(theme: String, content: @Composable () -> Unit) {

    val colorScheme = when (theme) {
        "DARK" -> darkColorScheme(
            background = Color(0xFF121212),
            surface = Color(0xFF121212),
            primary = Color(0xFF03A9F4),
            onPrimary = Color.White,
            onBackground = Color.White
        )
        "PINK" -> lightColorScheme(
            background = Color(0xFFFF4EC7),
            surface = Color(0xFFFF4EC7),
            primary = Color.White,
            onPrimary = Color.Black,
            onBackground = Color.Black
        )
        "BLUE" -> lightColorScheme(
            background = Color(0xFF6EB6FF),
            surface = Color(0xFF6EB6FF),
            primary = Color.White,
            onPrimary = Color.Black,
            onBackground = Color.Black
        )
        else -> lightColorScheme() // LIGHT default
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes(),
    ) {
        // Wrap with Surface so background applies to whole screen
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}
