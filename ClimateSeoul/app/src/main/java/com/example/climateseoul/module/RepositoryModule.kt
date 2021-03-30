package com.example.climateseoul.module

import com.example.climateseoul.climateinfo.datasource.DataSource
import com.example.climateseoul.climateinfo.repository.ClimateInfoRepository
import com.example.climateseoul.climateinfo.repository.ClimateInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideClimateInfoRepository(dataSource: DataSource): ClimateInfoRepository = ClimateInfoRepositoryImpl(dataSource)
}