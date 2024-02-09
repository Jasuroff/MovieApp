package com.example.examapp.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.examapp.R
import com.example.examapp.core.adapter.MultiAdapter
import com.example.examapp.core.model.base.BaseModel
import com.example.examapp.core.model.now.FilmsNowPlayingResponse
import com.example.examapp.core.model.popular.FilmsPopularResponse
import com.example.examapp.databinding.ScreenHomeBinding
import com.example.examapp.ui.home.vm.HomeScreenVM


class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val homeVM: HomeScreenVM by viewModels()
    private val data = MediatorLiveData<Pair<FilmsNowPlayingResponse?, FilmsPopularResponse?>>()
    val adapter by lazy { MultiAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVM.getFilmsData()
        observer()
        setAdapter()

    }

    private fun setAdapter() {
        binding.recyler.adapter = adapter
        binding.recyler.layoutManager = LinearLayoutManager(context)
        binding.recyler.setHasFixedSize(true)
    }

    private fun observer() {

        /*  homeVM.filmsNowLiveData.observe(requireActivity()) {
              multiData.add(it!!)
              one = true
              if (one && two){
                  this.adapter.setData(multiData)
              }
          }
          homeVM.filmsPopularLiveData.observe(requireActivity()){
              multiData.add(it!!)
              two = true
              if (one && two){
                  this.adapter.setData(multiData)
              }
          }

  */

        data.addSource(homeVM.filmsNowLiveData) { result1 ->
            data.value = Pair(result1, data.value?.second)
        }

        data.addSource(homeVM.filmsPopularLiveData) { result2 ->
            data.value = Pair(data.value?.first, result2)
        }
        data.observe(viewLifecycleOwner) {
            val data1 = it.first
            val data2 = it.second
            if (data1 != null && data2 != null) {
                adapter.addData(data1)
                adapter.addData(data2)


            }
        }


    }


}