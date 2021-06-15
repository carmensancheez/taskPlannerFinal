package com.example.taskplanner.repository.model.converters

import androidx.room.TypeConverter
import com.example.taskplanner.repository.model.entity.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(roles: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(roles)
    }

    @TypeConverter
    fun taskListToString(taskList: List<Task>?): String? {
        return  Gson().toJson(taskList)
    }
}