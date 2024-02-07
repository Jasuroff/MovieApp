package com.example.examapp.core.model.popular


import com.example.examapp.core.model.base.BaseModel
import com.google.gson.annotations.SerializedName

data class FilmsPopularResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<PopularResult>,
    @SerializedName("total_pages")
    val totalPages: Int, // 42646
    @SerializedName("total_results")
    val totalResults: Int // 852918
): BaseModel() {
    override fun getType(): Int {

        return POPULAR

    }
}