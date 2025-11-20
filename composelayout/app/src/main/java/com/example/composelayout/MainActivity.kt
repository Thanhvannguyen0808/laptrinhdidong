package com.example.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelayout.ui.theme.ComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutTheme {
                MainScreen()
            }
        }
    }
}

// Màn hình chính điều hướng
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("menu") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF4F7FB)
    ) {
        when (currentScreen) {
            "menu" -> MenuScreen { currentScreen = it }
            "box" -> BoxDemo { currentScreen = "menu" }
            "surface" -> SurfaceDemo { currentScreen = "menu" }
            "card" -> CardDemo { currentScreen = "menu" }
            "columnrow" -> ColumnRowDemo { currentScreen = "menu" }
            "profile" -> ProfileDemo { currentScreen = "menu" }
        }
    }
}

// MENU DEMO CHÍNH
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jetpack Compose Layout Demos", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Khám phá sức mạnh của Compose Layout",
                textAlign = TextAlign.Center,
                color = Color(0xFF1565C0),
                fontWeight = FontWeight.Bold
            )

            val buttons = listOf(
                "Box Layout" to "box",
                "Surface Layout" to "surface",
                "Card Layout" to "card",
                "Column + Row" to "columnrow",
                "Profile Page" to "profile"
            )

            buttons.forEach { (label, route) ->
                Button(
                    onClick = { onNavigate(route) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
                ) {
                    Text(label, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScaffold(title: String, onBack: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

// BOX DEMO
@Composable
fun BoxDemo(onBack: () -> Unit) {
    DemoScaffold(title = "Box Layout", onBack = onBack) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF92D515), RoundedCornerShape(12.dp)),
        ) {
            Text(
                "TOP-LEFT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
            )

            Text(
                "CENTER",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )

            Text(
                "BOTTOM-RIGHT",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp)
            )
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Box cho phép xếp chồng các phần tử, và ta có thể đặt chúng chéo nhau bằng cách dùng Alignment khác nhau.",
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}


// SURFACE DEMO
@Composable
fun SurfaceDemo(onBack: () -> Unit) {
    DemoScaffold(title = "Surface Layout", onBack = onBack) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(150.dp),
            shape = RoundedCornerShape(22.dp),
            color = Color(0xFFE3F2FD),
            shadowElevation = 8.dp,
            tonalElevation = 6.dp
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("Hello Surface!", fontSize = 20.sp, color = Color(0xFF1565C0))
            }
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Surface là phần tử nền trong Material3 — hỗ trợ màu, bóng, và độ nổi (Elevation).",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

// CARD DEMO
@Composable
fun CardDemo(onBack: () -> Unit) {
    DemoScaffold(title = "Card Layout", onBack = onBack) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Card Title", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Đây là ví dụ về Card có shadow và border.\n" +
                            "💬 Card trong Compose chính là mở rộng của Surface, dễ tùy chỉnh hơn CardView trong XML.",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// COLUMN + ROW DEMO
@Composable
fun ColumnRowDemo(onBack: () -> Unit) {
    DemoScaffold(title = "Column + Row Layout", onBack = onBack) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF9C4), RoundedCornerShape(12.dp))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Column Layout", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {}) { Text("Button 1") }
                Button(onClick = {}) { Text("Button 2") }
            }
        }

        Spacer(Modifier.height(12.dp))
        Text(
            "Column = sắp xếp dọc | Row = sắp xếp ngang.\nĐây là nền tảng của hệ thống Layout Compose.",
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

// PROFILE DEMO
@Composable
fun ProfileDemo(onBack: () -> Unit) {
    var followed by remember { mutableStateOf(false) }
    var followerCount by remember { mutableStateOf(120) }
    val scale by animateFloatAsState(if (followed) 1.05f else 1f)

    DemoScaffold(title = "Profile Page", onBack = onBack) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Color(0xFF64B5F6))
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Color.White, CircleShape)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray, CircleShape)
                        .border(3.dp, Color(0xFF64B5F6), CircleShape)
                )
                Spacer(Modifier.height(8.dp))
                Text("Nguyễn Thị Thanh Vân", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("University of Transport Ho Chi Minh City", color = Color.Gray)
                Text("Followers: $followerCount", color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = {
                            followed = !followed
                            followerCount += if (followed) 1 else -1
                        },
                        modifier = Modifier.scale(scale),
                        colors = ButtonDefaults.buttonColors(containerColor = if (followed) Color.Gray else Color(0xFF1976D2))
                    ) {
                        Text(if (followed) "Following" else "Follow")
                    }
                    OutlinedButton(onClick = {}) { Text("Message") }
                }
            }
        }
    }
}
