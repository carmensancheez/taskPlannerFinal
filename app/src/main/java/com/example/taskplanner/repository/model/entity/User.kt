package com.example.taskplanner.repository.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskplanner.repository.remote.dto.UserDto

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val password:String,
    val email:String,
    val profilePictureUrl:String
    ) {
    constructor(userDto: UserDto) : this(0, userDto.name, userDto.password, userDto.email,
    userDto.profilePictureUrl)
}