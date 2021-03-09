package com.example.daggerhiltstudy.network

import com.example.daggerhiltstudy.model.ResponseUsers
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("users/Kotlin/repos")
    suspend fun getUserList(): Response<ResponseUsers>
}