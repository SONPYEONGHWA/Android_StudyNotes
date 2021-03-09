package com.example.daggerhiltstudy

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DaggerHiltApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}