package com.example.taskplanner.network.dto

import java.util.*

data class TaskDto(
    val id: String,
    val description: String,
    val personResponsible:String,
    val dueDate: Date,
    val status: String,
    val userId: String
)