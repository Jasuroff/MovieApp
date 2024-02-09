package com.example.examapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examapp.core.model.base.BaseModel
import com.example.examapp.core.model.now.FilmsNowPlayingResponse
import com.example.examapp.core.model.popular.FilmsPopularResponse
import com.example.examapp.databinding.ItemBannerParentBinding
import com.example.examapp.databinding.ItemFilmsParentBinding

@Suppress("CAST_NEVER_SUCCEEDS")
class MultiAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<BaseModel>()

    fun setData(multiData: ArrayList<BaseModel>){
        this.data.clear()
        this.data.addAll(multiData)
        notifyDataSetChanged()
    }

    fun addData(data: BaseModel){
        this.data.add(data)
        this.data.sortBy { it.getType() }
        notifyItemInserted(this.data.size)
    }
    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    inner class BannerParentViewHolder(private val binding: ItemBannerParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = BannerAdapter()

        fun bindDataToBannerParent(data: FilmsNowPlayingResponse) {
            binding.recyclerForBanner.adapter = adapter
            adapter.setData(data = data.results)
        }


    }

    inner class PopularParentViewHolder(private val binding: ItemFilmsParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = PopularAdapter()
        fun bindDataToNewsParent(data: FilmsPopularResponse) {
            binding.recyclerForBanner.adapter = adapter
            adapter.setData(data.results)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            BaseModel.NOW_PLAYING -> {
                BannerParentViewHolder(
                    ItemBannerParentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                PopularParentViewHolder(
                    ItemFilmsParentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BaseModel.NOW_PLAYING -> {
                (holder as BannerParentViewHolder).bindDataToBannerParent(data = data[position] as FilmsNowPlayingResponse)
            }

            BaseModel.POPULAR -> {
                (holder as PopularParentViewHolder).bindDataToNewsParent(data = data[position] as FilmsPopularResponse)
            }
        }
    }
}