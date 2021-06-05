package com.example.taskplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskplanner.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun all(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task)
}