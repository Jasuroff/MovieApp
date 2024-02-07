package com.example.examapp.core.util

data class ResultWrapper<S>(
    val data :S? = null,
    val error:String? = null
)