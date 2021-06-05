package com.example.taskplanner.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskplanner.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun all(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)
}