package com.example.rxjavasample.searchmovie.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rxjavasample.searchmovie.local.dao.SearchDao
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity

@Database(entities = [SearchEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSearchDao(): SearchDao

}