package com.example.mapservice.mapservice.di

import com.example.mapservice.mapservice.map.data.repository.LocationRepository
import com.example.mapservice.mapservice.map.data.repository.LocationRepositoryImpl
import com.example.mapservice.mapservice.map.data.source.LocationSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLocationRepositroy(locationSource: LocationSource): LocationRepository = LocationRepositoryImpl(locationSource)
}