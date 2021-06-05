package com.example.taskplanner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskplanner.network.dto.TaskDto

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val personResponsible:String,
    val dueDate: String,
    val status: String,
    val userId: String
) {
    constructor(taskDto: TaskDto) : this(0, taskDto.description, taskDto.personResponsible,
        taskDto.dueDate, taskDto.status, taskDto.userId )
}