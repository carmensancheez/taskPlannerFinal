package com.example.taskplanner.repository.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskplanner.repository.model.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun all(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task)
}