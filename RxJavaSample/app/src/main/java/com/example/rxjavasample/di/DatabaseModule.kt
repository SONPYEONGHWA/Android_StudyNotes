package com.example.rxjavasample.di

import android.content.Context
import androidx.room.Room
import com.example.rxjavasample.searchmovie.local.database.AppDatabase
import com.example.rxjavasample.searchmovie.local.dao.SearchDao
import com.example.rxjavasample.searchmovie.local.repository.SearchHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideSearchDao(appDatabase: AppDatabase) = appDatabase.getSearchDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideSearchHistoryRespository(searchDao: SearchDao): SearchHistoryRepository =
        SearchHistoryRepository(searchDao)
}