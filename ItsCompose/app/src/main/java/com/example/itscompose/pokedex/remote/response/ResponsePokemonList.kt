package com.example.itscompose.pokedex.remote.response

import com.example.itscompose.pokedex.remote.entity.Result

data class ResponsePokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)