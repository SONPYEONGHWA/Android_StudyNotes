package com.example.climateseoul.climateinfo.repository

import com.example.climateseoul.climateinfo.model.IotVdata
import retrofit2.Response

interface ClimateInfoRepository {
    suspend fun getClimateInfo(apiKey: String): Response<IotVdata>
}