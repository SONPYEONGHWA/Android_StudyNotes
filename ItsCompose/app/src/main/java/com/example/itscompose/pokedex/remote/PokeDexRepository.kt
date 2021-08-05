package com.example.itscompose.pokedex.remote

import com.example.itscompose.pokedex.remote.response.ResponsePokemon
import com.example.itscompose.pokedex.remote.response.ResponsePokemonList
import com.example.itscompose.util.ResourceState

interface PokeDexRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): ResourceState<ResponsePokemonList>
    suspend fun getPokemon(name: String): ResourceState<ResponsePokemon>
}