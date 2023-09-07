package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.ResponseModel

class GetAllCharacters {

    private val repository = CharactersRepository()

    suspend fun characters():ResponseModel? = repository.getAllCharacters()
}