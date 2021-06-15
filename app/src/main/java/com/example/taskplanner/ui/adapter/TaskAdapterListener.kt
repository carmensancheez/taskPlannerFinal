package com.example.taskplanner.ui.adapter

import com.example.taskplanner.repository.model.entity.Task

interface TaskAdapterListener {

    fun onClicked(task: Task)
}
