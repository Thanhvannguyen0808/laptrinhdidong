package com.example.homework_t5.data.model

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)
