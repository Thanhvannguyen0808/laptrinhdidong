package com.example.homework_t5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework_t5.data.model.Task
import com.example.homework_t5.ui.components.TaskItem
import com.example.homework_t5.viewmodel.TaskViewModel

// Import màu sắc từ Theme (giả định đã được định nghĩa)
// Nếu bạn chưa có file TaskTheme.kt, cần tạo file này để định nghĩa các màu sau:
val PrimaryBlue = Color(0xFF1E88E5)
val BackgroundLight = Color(0xFFF3F6FC)
val TopBarSoftBlue = Color(0xFFDCEAF7)
val DarkText = Color(0xFF37474F)


// 🎯 Các icon Bottom Bar (FAB nằm giữa)
data class NavItem(val icon: ImageVector, val description: String)
val navItems = listOf(
    NavItem(Icons.Filled.Home, "Home"),
    NavItem(Icons.Filled.CalendarMonth, "Calendar"),
    NavItem(Icons.Filled.Search, "Search"),
    NavItem(Icons.Filled.Settings, "Settings")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onOpenDetail: (Int) -> Unit,
    onLogout: () -> Unit,
    onAddTask: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        // --- TOP BAR: Thiết kế lại Top Bar bo tròn theo mẫu ---
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(bottom = 1.dp)
            ) {
                CenterAlignedTopAppBar(
                    title = { Text("List", fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = DarkText) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = TopBarSoftBlue, // Màu nền xanh dương rất nhạt
                    ),
                    navigationIcon = {
                        // Icon Logout thay thế cho Back ở màn hình List
                        IconButton(onClick = onLogout) {
                            Icon(Icons.Filled.Logout, contentDescription = "Logout", tint = DarkText)
                        }
                    },
                    actions = {
                        // Icon ở góc phải trên (dùng Spacer để giữ vị trí)
                        Spacer(Modifier.width(48.dp))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)) // Bo tròn góc dưới
                )
            }
        },

        // --- BOTTOM NAVIGATION BAR ---
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    // Clip Top Bar để tạo cảm giác bo tròn
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                containerColor = Color.White, // Màu trắng chuẩn
                tonalElevation = 6.dp
            ) {
                IconButton(onClick = { /* Navigate Home */ }, modifier = Modifier.weight(1f)) {
                    Icon(navItems[0].icon, contentDescription = navItems[0].description, tint = PrimaryBlue) // Home Active
                }
                IconButton(onClick = { /* Navigate Calendar */ }, modifier = Modifier.weight(1f)) {
                    Icon(navItems[1].icon, contentDescription = navItems[1].description, tint = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1.4f)) // Khoảng trống cho FAB
                IconButton(onClick = { /* Navigate Search */ }, modifier = Modifier.weight(1f)) {
                    Icon(navItems[2].icon, contentDescription = navItems[2].description, tint = Color.Gray)
                }
                IconButton(onClick = { /* Navigate Settings */ }, modifier = Modifier.weight(1f)) {
                    Icon(navItems[3].icon, contentDescription = navItems[3].description, tint = Color.Gray)
                }
            }
        },

        // Floating Action Button (Nút +)
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                shape = RoundedCornerShape(percent = 50),
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task", modifier = Modifier.size(32.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = BackgroundLight // Nền tổng thể dịu mắt
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundLight),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.loading -> {
                    CircularProgressIndicator(color = PrimaryBlue)
                }

                state.tasks.isEmpty() -> {
                    // Màn hình List Empty (Card nền trắng, bo tròn lớn)
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(250.dp)
                            .align(Alignment.Center)
                            .offset(y = (-60).dp) // Dịch lên trên 1 chút
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Assignment,
                                contentDescription = "No Tasks",
                                modifier = Modifier.size(64.dp),
                                tint = DarkText
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("No Tasks Yet!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                            Text("Stay productive—add something to do", color = Color.LightGray, fontSize = 14.sp)
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.tasks) { task: Task ->
                            // Giả sử TaskItem được import chính xác
                            TaskItem(task = task, onClick = { onOpenDetail(task.id) })
                        }
                    }
                }
            }
        }
    }
}