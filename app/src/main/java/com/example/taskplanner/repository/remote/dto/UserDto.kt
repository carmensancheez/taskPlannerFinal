package com.example.taskplanner.repository.remote.dto

data class UserDto(
    val id:String,
    val name:String,
    val password:String,
    val email:String,
    val profilePictureUrl:String
)