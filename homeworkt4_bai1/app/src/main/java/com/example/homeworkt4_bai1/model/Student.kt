package com.example.homeworkt4_bai1.model

data class Student(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
)
