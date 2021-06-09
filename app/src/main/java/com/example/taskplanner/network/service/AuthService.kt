package com.example.taskplanner.network.service

import com.example.taskplanner.network.dto.LoginDto
import com.example.taskplanner.network.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth")
    suspend fun auth(@Body loginDto: LoginDto): Response<TokenDto>
}