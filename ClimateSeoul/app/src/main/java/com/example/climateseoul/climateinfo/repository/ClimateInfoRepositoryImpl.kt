package com.example.climateseoul.climateinfo.repository

import com.example.climateseoul.climateinfo.api.ClimateInfoService
import com.example.climateseoul.climateinfo.datasource.DataSource
import com.example.climateseoul.climateinfo.model.IotVdata
import retrofit2.Response
import javax.inject.Inject

class ClimateInfoRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
): ClimateInfoRepository {
    override suspend fun getClimateInfo(apiKey: String): Response<IotVdata> =
        dataSource.getClimateInfo(apiKey)
}