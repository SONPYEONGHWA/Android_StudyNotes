package com.example.itscompose.pokedex.remote.entity

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)