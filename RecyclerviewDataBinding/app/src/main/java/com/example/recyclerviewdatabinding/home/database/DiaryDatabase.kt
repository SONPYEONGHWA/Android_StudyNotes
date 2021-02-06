package com.example.recyclerviewdatabinding.home.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recyclerviewdatabinding.home.model.DiaryModel
import com.example.recyclerviewdatabinding.home.dao.DiaryDao

@Database(entities = [DiaryModel::class], version = 1, exportSchema = false)
abstract class DiaryDatabase(): RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase? {
            if (INSTANCE == null) {
                synchronized(DiaryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                    DiaryDatabase::class.java,
                    "diary_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}