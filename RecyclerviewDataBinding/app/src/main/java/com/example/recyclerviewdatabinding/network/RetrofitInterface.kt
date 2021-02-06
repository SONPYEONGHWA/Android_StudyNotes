package com.example.recyclerviewdatabinding.network

import com.example.recyclerviewdatabinding.search.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("/v2/search/web")
    fun searchInterface(@Header("Authorization") token: String, @Query("query") title: String): Call<SearchResponse>
}