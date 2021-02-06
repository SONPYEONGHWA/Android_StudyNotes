package com.example.recyclerviewdatabinding.search.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recyclerviewdatabinding.search.model.SearchResponse

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_table ORDER BY id ASC")
    fun getAll(): LiveData<List<SearchResponse.Documents>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searchList: List<SearchResponse.Documents>)

    @Insert
    fun insert(searchRoomEntity: SearchResponse.Documents)

    @Delete
    fun delete(searchRoomEntity: SearchResponse.Documents)
}