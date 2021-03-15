package com.example.mapservice.mapservice.di

import android.app.Activity
import android.content.Context
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import com.example.mapservice.mapservice.MainActivity
import com.example.mapservice.mapservice.application.MapServiceApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGeoceder(): Geocoder {
        return Geocoder(MapServiceApplication.ApplicationContext(), Locale.KOREAN)
    }

    @Provides
    @Singleton
    fun provideLocationManager(activity: MainActivity): LocationManager {
        return activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}