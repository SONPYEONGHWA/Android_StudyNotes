package com.example.itscompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ItsComposeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}