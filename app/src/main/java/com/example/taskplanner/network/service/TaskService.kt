package com.example.taskplanner.network.service

import com.example.taskplanner.network.dto.TaskDto
import retrofit2.Response
import retrofit2.http.GET

interface TaskService {

    @GET("tasks")
    suspend fun getTasksList(): Response<List<TaskDto>>


}