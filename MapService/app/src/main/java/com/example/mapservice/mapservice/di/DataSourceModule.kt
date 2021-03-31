package com.example.mapservice.mapservice.di

import com.example.mapservice.mapservice.map.data.source.LocationSource
import com.example.mapservice.mapservice.map.data.source.LocationSourceImpl
import com.example.mapservice.mapservice.map.data.source.SearchLocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocationSource(searchLocationService: SearchLocationService): LocationSource = LocationSourceImpl(searchLocationService)
}