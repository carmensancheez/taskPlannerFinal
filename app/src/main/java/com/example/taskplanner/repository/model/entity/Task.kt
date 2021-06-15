package com.example.taskplanner.repository.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskplanner.repository.remote.dto.TaskDto
import java.util.*

@Entity
data class Task(
    @PrimaryKey()
    val id: String,
    val description: String,
    val personResponsible:String,
    val dueDate: Date,
    val status: String,
    val userId: String
) {
    constructor(taskDto: TaskDto) : this(taskDto.id, taskDto.description, taskDto.personResponsible,
        taskDto.dueDate, taskDto.status, taskDto.userId )
}