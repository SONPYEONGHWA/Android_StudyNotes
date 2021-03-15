package com.example.mapservice.mapservice.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MapServiceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    init{
        instance = this
    }

    companion object {
        private var instance: MapServiceApplication? = null
        fun ApplicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}