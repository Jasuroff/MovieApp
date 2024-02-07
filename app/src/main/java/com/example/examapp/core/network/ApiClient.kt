package com.example.examapp.core.network

import com.example.examapp.core.network.service.ProjectService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getFilmsNowPlaying(): ProjectService {
        return createRetrofit().create(ProjectService::class.java)
    }

}