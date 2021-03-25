package com.example.rxjavasample.searchmovie.local.dao

import androidx.room.*
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_history")
    suspend fun getAll(): List<SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchList: SearchEntity)

    @Delete
    suspend fun delete(searchEntity: SearchEntity)

}