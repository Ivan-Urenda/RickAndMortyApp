package com.ivo.rickandmortyapp.domain.characters.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val specie: String,
    val status: String,
    val origin: String,
    val location: String
)
