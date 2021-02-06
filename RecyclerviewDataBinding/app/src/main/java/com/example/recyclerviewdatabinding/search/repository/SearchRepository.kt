package com.example.recyclerviewdatabinding.search.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.recyclerviewdatabinding.search.database.SearchDatabase
import com.example.recyclerviewdatabinding.search.dao.SearchDao
import com.example.recyclerviewdatabinding.search.model.SearchResponse
import java.lang.Exception

class SearchRepository(application: Application) {
    private lateinit var searchDao: SearchDao
    private var searchList: LiveData<List<SearchResponse.Documents>>

    init {
        val database: SearchDatabase? = SearchDatabase.getInstance(application)
        if(database != null){
            searchDao = database.searchDao()
        }
        searchList = searchDao.getAll()
    }

    fun insert(documents: SearchResponse.Documents){
        try{
            val thread = Thread(Runnable{
                searchDao.insert(documents)
            })
            thread.start()
        } catch (e: Exception){ e.printStackTrace() }
    }

    fun insertAll(documents: List<SearchResponse.Documents>){
        try {
            val thread = Thread(Runnable {
                searchDao.insertAll(documents)
            })
            thread.start()
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun getAll(): LiveData<List<SearchResponse.Documents>>{
        return searchList
    }

    fun delete(documents: SearchResponse.Documents){
        try {
            val thread = Thread(Runnable {
                searchDao.delete(documents)
            })
            thread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}