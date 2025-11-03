package com.example.homeworkt4_bai1.model

data class Book(
    val id: Int,
    val title: String,
    var isBorrowed: Boolean = false
)
