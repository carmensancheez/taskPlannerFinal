package com.example.taskplanner.network.dto

data class UserDto(
    val id:String,
    val name:String,
    val password:String,
    val email:String,
    val profilePictureUrl:String
)