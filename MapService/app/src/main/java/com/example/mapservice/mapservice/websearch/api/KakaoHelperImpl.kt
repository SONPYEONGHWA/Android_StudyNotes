package com.example.mapservice.mapservice.websearch.api

import com.example.mapservice.mapservice.websearch.model.SearchResponseModel
import retrofit2.Response
import javax.inject.Inject

class KakaoHelperImpl @Inject constructor(
    private val kakaoApiService: KakaoApiService
): KakaoHelper {
    override suspend fun getSearchResults(query: String): Response<SearchResponseModel> = kakaoApiService.getVideoResults(query)
}