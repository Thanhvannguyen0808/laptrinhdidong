package com.example.homeworkt4_bai1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.homeworkt4_bai1.model.LibraryManager
import com.example.homeworkt4_bai1.model.Student

@Composable
fun StudentScreen() {
    val manager = remember { LibraryManager() }
    val students = manager.getStudents()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Danh sách Sinh viên",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(students) { student ->
                StudentCard(student)
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(student.name, fontWeight = FontWeight.Bold)
            if (student.borrowedBooks.isEmpty()) {
                Text("Chưa mượn sách nào.", style = MaterialTheme.typography.bodySmall)
            } else {
                Text(
                    "Đang mượn: ${student.borrowedBooks.joinToString { it.title }}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
