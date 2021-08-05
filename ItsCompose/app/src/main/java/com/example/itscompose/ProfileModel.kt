package com.example.itscompose

data class ProfileModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int =0
)