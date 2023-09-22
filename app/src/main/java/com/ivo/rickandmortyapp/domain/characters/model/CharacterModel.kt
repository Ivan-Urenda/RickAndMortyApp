package com.ivo.rickandmortyapp.domain.characters.model

import com.ivo.rickandmortyapp.utils.Constants

data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val specie: String,
    val status: String,
    val origin: String,
    val location: String,
    val episodes: Int? = Constants.DEFAULT_EPISODES_VALUE
)
