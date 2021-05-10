package com.example.mapservice.mapservice.map.data.source

import com.example.mapservice.mapservice.map.data.dto.ResponseLocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchLocationService {
    @GET("/v2/local/search/keyword.json")
    fun getLocationSearched(@Query("query") query: String): Single<ResponseLocation>
}