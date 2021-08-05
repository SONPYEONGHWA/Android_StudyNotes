package com.example.itscompose.pokedex.remote

import com.example.itscompose.pokedex.remote.response.ResponsePokemon
import com.example.itscompose.pokedex.remote.response.ResponsePokemonList
import com.example.itscompose.util.ResourceState
import java.lang.Exception
import javax.inject.Inject

class PokeDexRepositoryImpl @Inject constructor(
    private val pokeDexApiInterface: PokeDexApiInterface
): PokeDexRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ResourceState<ResponsePokemonList> {
        val response = try {
            pokeDexApiInterface.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return ResourceState.ERROR("error")
        }
        return ResourceState.SUCCESS(response)
    }

    override suspend fun getPokemon(name: String): ResourceState<ResponsePokemon> {
        val response = try {
            pokeDexApiInterface.getPokemon(name)
        } catch (e: Exception) {
            return ResourceState.ERROR("error pokemon")
        }
        return ResourceState.SUCCESS(response)
    }

}