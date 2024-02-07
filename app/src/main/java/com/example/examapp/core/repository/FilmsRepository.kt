package com.example.examapp.core.repository

import com.example.examapp.core.model.now.FilmsNowPlayingResponse
import com.example.examapp.core.model.popular.FilmsPopularResponse
import com.example.examapp.core.network.ApiClient
import com.example.examapp.core.util.API_KEY
import com.example.examapp.core.util.ResultWrapper


class FilmsRepository {

    private val serviceNow = ApiClient.getFilmsNowPlaying()
    private val servicePopular = ApiClient.getFilmsNowPlaying()

    suspend fun getNowFilms(): ResultWrapper<FilmsNowPlayingResponse> {

        val response = serviceNow.getNowPlaying(API_KEY)

        if (response.isSuccessful){
            response.body()?.let { return ResultWrapper(it) }
        }

        return ResultWrapper(error = "Error")
    }


    suspend fun getPopularFilms(): ResultWrapper<FilmsPopularResponse> {
        val response = servicePopular.getPopular(API_KEY)

        if (response.isSuccessful){
            response.body()?.let { return ResultWrapper(it) }
        }
        return ResultWrapper(error = "Error")
    }

}