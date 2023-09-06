package com.ivo.rickandmortyapp.data

import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.network.CharacterService

class CharactersRepository {

    private val api = CharacterService()

    suspend fun getAllCharacters(): ResponseModel {
        val response = api.getCharacters()
        //characterProvider.characters = response
        return response
    }
}