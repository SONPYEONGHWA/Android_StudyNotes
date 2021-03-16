package com.example.mapservice.mapservice.di

import android.location.Geocoder
import com.example.mapservice.mapservice.application.MapServiceApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGeoceder(): Geocoder {
        return Geocoder(MapServiceApplication.ApplicationContext(), Locale.KOREAN)
    }
}