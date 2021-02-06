package com.example.recyclerviewdatabinding.search.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recyclerviewdatabinding.search.dao.SearchDao
import com.example.recyclerviewdatabinding.search.model.SearchResponse

@Database(entities = [SearchResponse.Documents::class], version = 1, exportSchema = false)
abstract class SearchDatabase(): RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object {
        private var INSTANCE: SearchDatabase? = null
        fun getInstance(context: Context): SearchDatabase? {
            if(INSTANCE == null){
                synchronized(SearchDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, SearchDatabase::class.java, "search_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}