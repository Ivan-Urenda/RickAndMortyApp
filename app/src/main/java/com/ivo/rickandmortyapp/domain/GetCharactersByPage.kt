package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.ResponseModel

class GetCharactersByPage {

    private val repository = CharactersRepository()

    suspend fun charactersByPage(url: String): ResponseModel? = repository.getCharactersByPage(url)
}