package com.example.recyclerviewdatabinding.home.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.recyclerviewdatabinding.home.dao.DiaryDao
import com.example.recyclerviewdatabinding.home.database.DiaryDatabase
import com.example.recyclerviewdatabinding.home.model.DiaryModel
import java.lang.Exception

class DiaryRepository(application: Application) {
    private lateinit var diaryDao: DiaryDao
    private var diaryList: LiveData<List<DiaryModel>>

    init {
        val database: DiaryDatabase? = DiaryDatabase.getInstance(application)
        if (database != null) {
            diaryDao = database.diaryDao()
        }
        diaryList = diaryDao.getAll()
    }

    fun insert(diaryModel: DiaryModel){
        try {
            val thread = Thread(Runnable {
                diaryDao.insert(diaryModel)
            })
            thread.start()
        } catch (e:Exception) {e.printStackTrace()}
    }

    fun delete(diaryModel: DiaryModel) {

        try {
            val thread = Thread(Runnable {
                diaryDao.delete(diaryModel)
            })
            thread.start()
        } catch (e:Exception) { e.printStackTrace() }

    }

    fun getAll() : LiveData<List<DiaryModel>>{
        return diaryList
    }
}