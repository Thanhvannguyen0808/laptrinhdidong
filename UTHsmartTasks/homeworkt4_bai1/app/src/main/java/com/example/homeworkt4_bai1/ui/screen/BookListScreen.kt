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
import com.example.homeworkt4_bai1.model.Book
import com.example.homeworkt4_bai1.model.LibraryManager

@Composable
fun BookListScreen() {
    val manager = remember { LibraryManager() }
    val books = manager.getAllBooks()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Danh sách Sách",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(books) { book ->
                val borrower = manager.getBorrowerOfBook(book)
                BookRow(book = book, borrower = borrower)
            }
        }
    }
}

@Composable
fun BookRow(book: Book, borrower: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(book.title, fontWeight = FontWeight.Bold)
                borrower?.let {
                    Text("Đang được mượn bởi: $it", style = MaterialTheme.typography.bodySmall)
                } ?: Text("Chưa được mượn", style = MaterialTheme.typography.bodySmall)
            }
            if (borrower != null) {
                AssistChip(onClick = {}, label = { Text("Đã mượn") })
            } else {
                AssistChip(onClick = {}, label = { Text("Có sẵn") })
            }
        }
    }
}
