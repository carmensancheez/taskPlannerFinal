package com.example.taskplanner.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskplanner.R
import com.example.taskplanner.repository.model.entity.Task
import com.example.taskplanner.repository.remote.dto.TaskDto

class TaskAdapter(
    private val taskAdapterListener: TaskAdapterListener
    ): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var taskList: List<Task> = ArrayList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val status: TextView = view.findViewById(R.id.status)
        val dueDate: TextView = view.findViewById(R.id.dueDate)
        val description: TextView = view.findViewById(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.description.text = task.description
        holder.dueDate.text = task.dueDate.toString()
        holder.status.text = task.status
    }

    fun updateTaskList(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}