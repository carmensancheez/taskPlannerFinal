package com.example.taskplanner.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplanner.repository.UserRepository
import com.example.taskplanner.repository.model.entity.User
import com.example.taskplanner.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
//    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository,
    private val storage: Storage
) : ViewModel() {

    var user: User? = null

    val successLiveData = MutableLiveData<Boolean>()


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
}