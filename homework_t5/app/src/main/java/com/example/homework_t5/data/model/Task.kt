package com.example.homework_t5.data.model

data class Subtask(val id: Int? = null, val title: String? = null, val done: Boolean? = null)
data class Attachment(val id: Int? = null, val fileName: String? = null, val url: String? = null)

data class Task(
    val id: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val status: String? = null,
    val priority: String? = null,
    val category: String? = null,
    val time: String? = null,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<Attachment> = emptyList()
)
