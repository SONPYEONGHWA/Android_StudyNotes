package com.example.mapservice.mapservice.websearch.api

import com.example.mapservice.mapservice.websearch.model.SearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("/v2/search/vclip")
    suspend fun getVideoResults(@Query("query") query: String): Response<SearchResponseModel>
}