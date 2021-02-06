package com.example.recyclerviewdatabinding.search.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchResponse(
    val meta: Meta,
    val documents: MutableList<Documents>
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )
    @Entity(tableName = "search_table")
    data class Documents(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "datetime")
        val datetime: String,
        @ColumnInfo(name = "contents")
        val contents: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "url")
        val url: String
    )
}