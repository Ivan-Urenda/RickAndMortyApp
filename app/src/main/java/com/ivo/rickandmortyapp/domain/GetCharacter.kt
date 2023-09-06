package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel

class GetCharacter {

    private val repository = CharactersRepository()

    suspend fun character(url: String): ResultsModel = repository.getCharacter(url)
}