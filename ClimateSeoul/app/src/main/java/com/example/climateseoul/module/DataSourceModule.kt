package com.example.climateseoul.module

import com.example.climateseoul.climateinfo.api.ClimateInfoService
import com.example.climateseoul.climateinfo.datasource.DataSource
import com.example.climateseoul.climateinfo.datasource.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideClimateDataSource(climateInfoService: ClimateInfoService): DataSource = DataSourceImpl(climateInfoService)
}