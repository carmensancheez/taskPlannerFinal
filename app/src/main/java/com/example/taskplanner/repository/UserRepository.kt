package com.example.taskplanner.repository

import com.example.taskplanner.repository.model.dao.UserDao
import com.example.taskplanner.repository.remote.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(val userService: UserService, val userDao: UserDao) {

}