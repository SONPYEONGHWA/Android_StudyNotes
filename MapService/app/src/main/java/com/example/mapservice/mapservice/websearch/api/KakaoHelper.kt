package com.example.mapservice.mapservice.websearch.api

import com.example.mapservice.mapservice.websearch.model.SearchResponseModel
import retrofit2.Response

interface KakaoHelper {
    suspend fun getSearchResults(query: String): Response<SearchResponseModel>
}