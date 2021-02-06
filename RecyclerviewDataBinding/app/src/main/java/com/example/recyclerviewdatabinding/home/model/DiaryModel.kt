package com.example.recyclerviewdatabinding.home.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_table")
data class DiaryModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "contents")
    val contents: String
)