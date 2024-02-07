package com.example.examapp.core.network.service

import com.example.examapp.core.model.now.FilmsNowPlayingResponse
import com.example.examapp.core.model.popular.FilmsPopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectService {

    @GET("/3/movie/now_playing")
    fun getNowPlaying(@Query("api_key") apiKey:String) :Response<FilmsNowPlayingResponse>

    @GET("/3/movie/popular")
    fun getPopular(@Query("api_key") apiKey:String) :Response<FilmsPopularResponse>

}