package com.example.examapp.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.examapp.R
import com.example.examapp.core.adapter.MultiAdapter
import com.example.examapp.core.model.base.BaseModel
import com.example.examapp.databinding.ScreenHomeBinding
import com.example.examapp.ui.home.vm.HomeScreenVM


class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val homeVM: HomeScreenVM by viewModels()
    private val multiData = ArrayList<BaseModel>()
    private var one = false
    private var two = false
    private val adapter by lazy { MultiAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVM.getFilmsData()
        observer()
        setAdapter()

    }

    private fun setAdapter() {
        multiData.clear()
        binding.recyler.adapter = adapter
        binding.recyler.layoutManager = LinearLayoutManager(context)
        binding.recyler.setHasFixedSize(true)
    }

    private fun observer() {

        homeVM.filmsNowLiveData.observe(requireActivity()) {
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




    }


}