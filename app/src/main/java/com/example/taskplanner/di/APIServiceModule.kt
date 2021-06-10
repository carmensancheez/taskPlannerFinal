package com.example.taskplanner.di

import com.example.taskplanner.BuildConfig
import com.example.taskplanner.network.AuthInterceptor
import com.example.taskplanner.network.service.AuthService
import com.example.taskplanner.network.service.TaskService
import com.example.taskplanner.network.service.UserService
import com.example.taskplanner.storage.Storage
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIServiceModule {
    @Provides
    @Singleton
    fun provideRetrofit(storage: Storage): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(storage))
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()
        return builder.client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideTaskService(retrofit: Retrofit): TaskService {
        return retrofit.create(TaskService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}