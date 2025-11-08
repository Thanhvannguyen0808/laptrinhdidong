package com.example.homework_t5.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homework_t5.viewmodel.TaskViewModel
import com.example.homework_t5.data.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(taskId: Int, viewModel: TaskViewModel, onBack: () -> Unit) {
    val detailState by viewModel.taskDetailState.collectAsState()
    LaunchedEffect(taskId) { viewModel.loadTask(taskId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteTask(taskId) { success, _ ->
                            if (success) onBack()
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                detailState == null -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                detailState?.isSuccess == true -> {
                    val task = detailState!!.getOrNull()!!
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        item {
                            Text(task.title ?: "", style = MaterialTheme.typography.titleLarge)
                            Spacer(Modifier.height(8.dp))
                            Text(task.description ?: "")
                            Spacer(Modifier.height(12.dp))
                            Text("Category: ${task.category ?: "-"}")
                            Spacer(Modifier.height(6.dp))
                            Text("Status: ${task.status ?: "-"}")
                            Spacer(Modifier.height(12.dp))
                            Text("Subtasks:", style = MaterialTheme.typography.titleMedium)
                        }
                        items(task.subtasks) { st ->
                            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                                Checkbox(checked = st.done == true, onCheckedChange = null)
                                Spacer(Modifier.width(8.dp))
                                Text(st.title ?: "")
                            }
                        }
                        item {
                            Spacer(Modifier.height(12.dp))
                            Text("Attachments:", style = MaterialTheme.typography.titleMedium)
                        }
                        items(task.attachments) { att ->
                            Text(att.fileName ?: att.url ?: "", modifier = Modifier.padding(vertical = 6.dp))
                        }
                    }
                }
                detailState?.isFailure == true -> {
                    Text("Error: ${detailState!!.exceptionOrNull()?.localizedMessage}", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
