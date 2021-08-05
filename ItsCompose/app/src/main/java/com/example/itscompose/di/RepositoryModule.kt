package com.example.itscompose.di

import com.example.itscompose.pokedex.remote.PokeDexApiInterface
import com.example.itscompose.pokedex.remote.PokeDexRepository
import com.example.itscompose.pokedex.remote.PokeDexRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokeDexRepository(pokeDexApiInterface: PokeDexApiInterface): PokeDexRepository =
        PokeDexRepositoryImpl(pokeDexApiInterface)
}