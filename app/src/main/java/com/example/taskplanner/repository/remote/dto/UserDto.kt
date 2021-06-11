package com.example.taskplanner.repository.remote.dto

data class UserDto(
    val id:String,
    val name:String,
    val passwordHash:String,
    val email:String,
    val profilePictureUrl:String,
    val roles: List<String>
)