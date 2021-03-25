package com.example.rxjavasample.searchmovie.local.repository

import com.example.rxjavasample.searchmovie.local.dao.SearchDao
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity
import io.reactivex.Completable
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val searchDao: SearchDao
){
    fun insert(searchEntity: SearchEntity)= searchDao.insert(searchEntity)
    fun getAll() = searchDao.getAll()
    fun delete(searchEntity: SearchEntity) = searchDao.delete(searchEntity)
}