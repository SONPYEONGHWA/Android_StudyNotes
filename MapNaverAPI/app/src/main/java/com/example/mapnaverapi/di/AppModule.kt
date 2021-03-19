package com.example.mapnaverapi.di

import android.app.Application
import android.content.Context
import android.location.Geocoder
import com.naver.maps.map.overlay.Marker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder{
        return Geocoder(context, Locale.KOREAN)
    }
}