package com.example.taskplanner.repository.remote.task

import com.example.taskplanner.repository.remote.dto.TaskDto
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @GET("tasks")
    suspend fun getTasksList(): Response<List<TaskDto>>

    @POST("tasks")
    suspend fun createTask(@Body taskDto: TaskDto): Response<TaskDto>

    @GET("tasks/{id}")
    suspend fun findTaskById(@Path("id") id: String): Response<TaskDto>

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id")id: String): Response<TaskDto>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id")id: String): Response<TaskDto>
}