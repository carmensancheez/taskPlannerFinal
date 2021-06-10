package com.example.taskplanner.repository.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskplanner.repository.model.dao.TaskDao
import com.example.taskplanner.repository.model.dao.UserDao
import com.example.taskplanner.repository.model.entity.Task
import com.example.taskplanner.repository.model.entity.User
import com.example.taskplanner.repository.model.converters.Converters

@Database(entities = [Task::class, User::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class TaskAppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun UserDao(): UserDao
}
