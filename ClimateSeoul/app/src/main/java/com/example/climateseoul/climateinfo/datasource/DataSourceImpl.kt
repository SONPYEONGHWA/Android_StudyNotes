package com.example.climateseoul.climateinfo.datasource

import com.example.climateseoul.climateinfo.api.ClimateInfoService
import com.example.climateseoul.climateinfo.model.IotVdata
import retrofit2.Response
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val climateInfoService: ClimateInfoService
): DataSource {
    override suspend fun getClimateInfo(apiKey: String): Response<IotVdata> =
        climateInfoService.getClimateInfo(apiKey)
}