package com.example.homeworkt4_bai1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.homeworkt4_bai1.model.LibraryManager
import com.example.homeworkt4_bai1.model.Student
import com.example.homeworkt4_bai1.model.Book

@Composable
fun ManageScreen() {
    val manager = remember { LibraryManager() }
    var selectedStudent by remember { mutableStateOf(manager.getStudents().first()) }

    val borrowedBooks by remember { derivedStateOf { manager.getBorrowedBooks(selectedStudent) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Sinh viên", fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = selectedStudent.name,
                onValueChange = {},
                modifier = Modifier.weight(1f),
                readOnly = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                val list = manager.getStudents()
                val idx = list.indexOf(selectedStudent)
                selectedStudent = list[(idx + 1) % list.size]
            }) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Danh sách sách", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 160.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            if (borrowedBooks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(borrowedBooks) { book ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = { manager.toggleBookBorrowed(selectedStudent, book) },
                                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.error)
                            )
                            Text(book.title, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val allBooks = manager.getAllBooks()
                val nextBook = allBooks.firstOrNull { it !in selectedStudent.borrowedBooks }
                nextBook?.let { manager.addBookToStudent(selectedStudent, it) }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Thêm")
        }
    }
}
