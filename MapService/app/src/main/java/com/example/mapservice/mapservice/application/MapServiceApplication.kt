package com.example.mapservice.mapservice.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MapServiceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}