package com.example.taskplanner.storage

import android.content.Context
import com.example.taskplanner.utils.SHARED_PREFERENCES_FILE_NAME
import com.example.taskplanner.utils.TOKEN_KEY

class LocalStorage(context: Context) : Storage {

    private val sharedPref =
        context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPref.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String {
        return sharedPref.getString(TOKEN_KEY, "")!!
    }

    override fun clear() {
        return sharedPref.edit()
            .remove(TOKEN_KEY)
            .apply()
    }
}