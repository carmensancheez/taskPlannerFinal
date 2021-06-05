package com.example.taskplanner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskplanner.data.dao.TaskDao
import com.example.taskplanner.data.dao.UserDao
import com.example.taskplanner.data.entity.Task
import com.example.taskplanner.data.entity.User

@Database(entities = [Task::class, User::class], version = 1, exportSchema = false)
abstract class TaskAppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun UserDao(): UserDao
}