package com.example.taskplanner.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.taskplanner.R
import com.example.taskplanner.repository.remote.dto.LoginDto
import com.example.taskplanner.repository.remote.auth.AuthService
import com.example.taskplanner.storage.Storage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    @Inject
    lateinit var authService: AuthService

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth()
    }

    private fun auth() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = authService.auth(LoginDto("davidcab11@gmail.com", "passw0rd"))
//            val response = authService.auth(LoginDto("david@gmail.com", "passw0rd"))
            if (response.isSuccessful) {
                val tokenDto = response.body()!!
                Log.d("Developer", "tokenDto $tokenDto")
                storage.saveToken(tokenDto.token)
//                startActivity(Intent(application, SecondFragment()))
            } else {
                response.errorBody()
            }
        }
    }
}