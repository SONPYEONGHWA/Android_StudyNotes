package com.example.mapservice.mapservice.websearch.repository

import com.example.mapservice.mapservice.websearch.api.KakaoHelper
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val kakaoHelper: KakaoHelper
) {
    suspend fun getSearchResults(query: String) = kakaoHelper.getSearchResults(query)
}