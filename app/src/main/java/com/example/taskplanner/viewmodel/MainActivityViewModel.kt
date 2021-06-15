package com.example.taskplanner.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.room.TypeConverters
import com.example.taskplanner.repository.TaskRepository
import com.example.taskplanner.repository.UserRepository
import com.example.taskplanner.repository.model.entity.Task
import com.example.taskplanner.repository.model.entity.User
import com.example.taskplanner.repository.remote.dto.TaskDto
import com.example.taskplanner.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
//    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository,
    private val taskRepository: TaskRepository,
    private val storage: Storage
) : ViewModel() {

    var user: User? = null

    val successLiveData = MutableLiveData<Boolean>()

    val taskListLiveData: LiveData<List<Task>> = taskRepository.taskDao.all()

    val errorMessageLiveData = MutableLiveData<String>()

    fun syncTaskData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskRepository.syncData()
            } catch (e: Exception) {
                Log.e("Developer", "Error on syncData", e)
                errorMessageLiveData.postValue("No Internet Connection")
            }

        }
    }

    fun findUserById() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.userService.findUserById("6090acc6d980b010af3e278e")
                if (response.isSuccessful) {
                    val user = response.body()!!
                    Log.d("Developer", "tokenDto:  $user")
                    this@MainActivityViewModel.user = User(user)
                    successLiveData.postValue(true)
                    repository.userDao.save(this@MainActivityViewModel.user!!)

                } else {
                    if (response.raw().code == 403) {
                        storage.clear()
                    }
                }
            } catch (e: Exception) {
                Log.d("Developer", "Exception happen", e)
            }
        }
    }

//    fun syncTaskData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = taskRepository.taskService.getTasksList()
//            if(response.isSuccessful) {
//                val tasks: List<TaskDto> = response.body()!!
//                Log.d("Developer", "$tasks, entre")
//                successLiveData2.postValue(true)
//                this@MainActivityViewModel.taskList = tasks
//            } else {
//                Log.d("Developer", "falle")
//
//            }
//        }
//    }
}