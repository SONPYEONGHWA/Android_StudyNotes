package com.example.itscompose.pokedex.remote

import com.example.itscompose.pokedex.remote.response.ResponsePokemonList
import com.example.itscompose.pokedex.remote.response.ResponsePokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeDexApiInterface {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query ("limit") limit: Int,
        @Query ("offset") offset: Int
    ): ResponsePokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path ("name") name: String
    ): ResponsePokemon
}