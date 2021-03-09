package com.example.daggerhiltstudy.repository

import com.example.daggerhiltstudy.network.RetrofitService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val retrofitService: RetrofitService
) {
    suspend fun getUserList() = retrofitService.getUserList()
}