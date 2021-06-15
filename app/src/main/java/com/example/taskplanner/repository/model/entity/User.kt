package com.example.taskplanner.repository.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskplanner.repository.remote.dto.UserDto

@Entity
data class User(
    @PrimaryKey()
    var id: String,
    val name:String,
//    val passwordHash:String,
    val email:String,
    val profilePictureUrl:String,
    val roles: List<String>
    ) {

    constructor(userDto: UserDto) : this(userDto.id, userDto.name,
//        userDto.passwordHash,
        userDto.email, userDto.profilePictureUrl, userDto.roles)

//    fun isUploadServer() = return  id != null

    fun isUploadServer(): Boolean {
        if (id != null) return true
        return false
    }
}

