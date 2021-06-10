package com.example.taskplanner.network.service

import com.example.taskplanner.network.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("users")
    suspend fun createUser(@Body userDto: UserDto): Response<UserDto>

    @GET("users")
    suspend fun getUsersList(): Response<List<UserDto>>

    @GET("users/{id}")
    suspend fun findUserByEmail(@Path("id") id: String): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id")id: String): Response<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id")id: String): Response<UserDto>
}