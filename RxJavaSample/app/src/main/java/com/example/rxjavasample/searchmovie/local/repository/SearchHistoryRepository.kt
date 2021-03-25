package com.example.rxjavasample.searchmovie.local.repository

import com.example.rxjavasample.searchmovie.local.dao.SearchDao
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(
    private val searchDao: SearchDao
){
    suspend fun insert(searchEntity: SearchEntity) = searchDao.insert(searchEntity)
    suspend fun getAll() = searchDao.getAll()
    suspend fun delete(searchEntity: SearchEntity) = searchDao.delete(searchEntity)
}