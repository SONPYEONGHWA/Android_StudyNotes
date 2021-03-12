package com.example.mapservice.mapservice.websearch.model

import com.google.gson.annotations.SerializedName

data class SearchResponseModel(
    val meta: Meta,
    val documents: List<DocumentsModel>?
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )

    data class DocumentsModel(
        val title: String,
        val play_time: Int,
        val thumbnail: String,
        val url: String,
        val datetime: String,
        val author: String
    )
}
