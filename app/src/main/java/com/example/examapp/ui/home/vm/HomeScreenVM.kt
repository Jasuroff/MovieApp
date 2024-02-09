package com.example.examapp.ui.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.examapp.base.BaseViewModel
import com.example.examapp.core.model.now.FilmsNowPlayingResponse
import com.example.examapp.core.model.popular.FilmsPopularResponse
import com.example.examapp.core.model.popular.PopularResult
import com.example.examapp.core.repository.FilmsRepository
import kotlinx.coroutines.launch

class HomeScreenVM : BaseViewModel() {

    private val repository = FilmsRepository()
    val filmsPopularLiveData: MutableLiveData<FilmsPopularResponse> = MutableLiveData()
    val filmsNowLiveData: MutableLiveData<FilmsNowPlayingResponse> = MutableLiveData()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()

    fun getFilmsData() {//filmsNowLiveData

        viewModelScope.launch {
            val resultNow = repository.getNowFilms()
            val resultPopular = repository.getPopularFilms()

            if (resultNow != null) {
                resultNow.let {
                    filmsNowLiveData.value = it
                }


            } else {
                errorLiveData.value = "ERROR"
            }
            resultPopular.data!!.also { filmsPopularLiveData.value = it }
        }

    }
    fun getPopular(){
        launch {
            val result =repository.getPopularFilms()
            if (result.data!=null) {
                result.data.let {
                    filmsPopularLiveData.value=it
                }
              // filmsNowLiveData.value=result.data
            }else{
                errorLiveData.value="Error"
            }
        }
    }

}