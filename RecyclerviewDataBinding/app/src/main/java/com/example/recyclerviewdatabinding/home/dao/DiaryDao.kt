package com.example.recyclerviewdatabinding.home.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recyclerviewdatabinding.home.model.DiaryModel

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_table")
    fun getAll(): LiveData<List<DiaryModel>>

    @Insert
    fun insert(diaryModel: DiaryModel)

    @Delete
    fun delete(diaryModel: DiaryModel)
}