package com.example.taskplanner.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskplanner.repository.remote.auth.AuthService
import com.example.taskplanner.repository.remote.dto.LoginDto
import com.example.taskplanner.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(private val authService: AuthService, private val storage: Storage): ViewModel() {


    val successLiveData = MutableLiveData<Boolean>()

    fun auth() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authService.auth(LoginDto("davidcab11@gmail.com", "passw0rd"))
            if (response.isSuccessful) {
                val tokenDto = response.body()!!
                Log.d("Developer", "tokenDto:  $tokenDto")
                storage.saveToken(tokenDto.token)
                successLiveData.postValue(true)
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

}