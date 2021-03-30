package com.example.climateseoul.climateinfo.api

import com.example.climateseoul.climateinfo.model.IotVdata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClimateInfoService {
    @GET("{api_key}/json/IotVdata017/1/30/")
    suspend fun getClimateInfo(@Path("api_key") apiKey: String): Response<IotVdata>
}