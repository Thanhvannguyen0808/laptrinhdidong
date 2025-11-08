package com.example.homework_t5.data.network

import com.example.homework_t5.data.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("api/researchUTH/tasks")
    suspend fun getTasks(): Response<ApiResponse<List<Task>>>

    @GET("api/researchUTH/task/{id}")
    suspend fun getTask(@Path("id") id: Int): Response<ApiResponse<Task>>

    @DELETE("api/researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<ApiResponse<Unit>>

    companion object {
        private const val BASE_URL = "https://amock.io/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
