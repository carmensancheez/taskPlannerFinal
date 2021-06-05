package com.example.taskplanner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskplanner.network.dto.UserDto

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