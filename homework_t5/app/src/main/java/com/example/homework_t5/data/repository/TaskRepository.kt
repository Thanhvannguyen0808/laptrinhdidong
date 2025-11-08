package com.example.homework_t5.data.repository

import com.example.homework_t5.data.model.ApiResponse
import com.example.homework_t5.data.model.Task
import com.example.homework_t5.data.network.ApiService
import retrofit2.Response

class TaskRepository(
    private val api: ApiService = ApiService.create()
) {
    suspend fun getTasks(): Response<ApiResponse<List<Task>>> = api.getTasks()
    suspend fun getTask(id: Int): Response<ApiResponse<Task>> = api.getTask(id)
    suspend fun deleteTask(id: Int): Response<ApiResponse<Unit>> = api.deleteTask(id)
}
