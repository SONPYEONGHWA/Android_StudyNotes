package com.example.rxjavasample.searchmovie.local.dao

import androidx.room.*
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_history")
    fun getAll(): Maybe<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchList: SearchEntity)

    @Delete
    fun delete(searchEntity: SearchEntity)
}