package com.example.examapp.core.model.now


import com.example.examapp.core.model.base.BaseModel
import com.google.gson.annotations.SerializedName

data class FilmsNowPlayingResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<BannerResult>,
    @SerializedName("total_pages")
    val totalPages: Int, // 168
    @SerializedName("total_results")
    val totalResults: Int // 3342
) : BaseModel() {
    override fun getType(): Int {

        return NOW_PLAYING

    }
}