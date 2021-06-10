package com.example.taskplanner.di

import android.content.Context
import androidx.room.Room
import com.example.taskplanner.repository.model.TaskAppDatabase
import com.example.taskplanner.repository.model.dao.TaskDao
import com.example.taskplanner.repository.model.dao.UserDao
import com.example.taskplanner.storage.LocalStorage
import com.example.taskplanner.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): TaskAppDatabase {
        return Room.databaseBuilder(context, TaskAppDatabase::class.java,
            "taskPlanner-database").build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: TaskAppDatabase): TaskDao {
        return appDatabase.taskDao()
    }
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: TaskAppDatabase): UserDao {
        return appDatabase.UserDao()
    }

    @Provides
    @Singleton
    fun provideStorage(@ApplicationContext context: Context): Storage {
        return LocalStorage(context)
    }
}