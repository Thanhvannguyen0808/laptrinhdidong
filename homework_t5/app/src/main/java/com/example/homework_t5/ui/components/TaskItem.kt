package com.example.homework_t5.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework_t5.data.model.Task

// --- 🎨 Chọn màu nền theo trạng thái ---
private fun getCardColor(status: String?): Color {
    return when (status?.lowercase()) {
        "completed" -> Color(0xFFFFC107) // 🌕 vàng đậm (amber) nổi bật
        "pending" -> Color(0xFFFFD54F)   // 🌤 vàng nhạt hơn một chút
        "in progress" -> Color(0xFFFFB300) // 🟡 vàng cam đậm
        else -> Color(0xFFFFE082)        // 🍯 vàng pastel mặc định
    }
}

// --- 🧩 Icon theo trạng thái ---
private fun getStatusIcon(status: String?): androidx.compose.ui.graphics.vector.ImageVector {
    return when (status?.lowercase()) {
        "completed" -> Icons.Filled.CheckCircle
        "pending" -> Icons.Filled.Pending
        else -> Icons.Filled.HourglassEmpty
    }
}

@Composable
fun TaskItem(task: Task, onClick: (Int) -> Unit) {
    val bgColor = getCardColor(task.status)
    val icon = getStatusIcon(task.status)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick(task.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 🌟 Icon trạng thái
            Icon(
                imageVector = icon,
                contentDescription = "status",
                tint = Color.White,
                modifier = Modifier
                    .size(36.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                // 🏷 Tiêu đề
                Text(
                    text = task.title ?: "(No title)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                // 📄 Mô tả ngắn
                Text(
                    text = task.description ?: "",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(6.dp))
                // 🕒 Trạng thái & thời gian
                Text(
                    text = "Status: ${task.status ?: "-"} | Time: ${task.time ?: "--:--"}",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}
