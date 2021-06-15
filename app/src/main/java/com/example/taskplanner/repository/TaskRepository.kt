package com.example.taskplanner.repository

import com.example.taskplanner.repository.model.dao.TaskDao
import com.example.taskplanner.repository.model.entity.Task
import com.example.taskplanner.repository.remote.task.TaskService
import javax.inject.Inject

class TaskRepository@Inject constructor(val taskService: TaskService, val taskDao: TaskDao) {

    suspend fun syncData() {
        val response = taskService.getTasksList()
        if (response.isSuccessful) {
            val taskList = response.body()!!
            taskList.forEach { taskDto ->
                taskDao.save(Task(taskDto))
            }
        }
    }
}