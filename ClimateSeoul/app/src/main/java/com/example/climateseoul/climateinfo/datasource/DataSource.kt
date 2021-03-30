package com.example.climateseoul.climateinfo.datasource

import com.example.climateseoul.climateinfo.model.IotVdata
import retrofit2.Response

interface DataSource {
    suspend fun getClimateInfo(apiKey: String): Response<IotVdata>
}