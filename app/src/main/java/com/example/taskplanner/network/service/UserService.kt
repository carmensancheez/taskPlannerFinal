package com.example.taskplanner.network.service

import com.example.taskplanner.network.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsersList(): Response<List<UserDto>>


}