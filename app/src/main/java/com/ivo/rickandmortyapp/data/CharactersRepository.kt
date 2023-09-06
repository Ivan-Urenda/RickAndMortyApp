package com.ivo.rickandmortyapp.data

import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.data.network.CharacterService

class CharactersRepository {

    private val api = CharacterService()

    suspend fun getAllCharacters(): ResponseModel {
        return api.getAllCharacters()
    }

    suspend fun getCharacter(url: String): ResultsModel {
        return api.getCharacter(url)
    }
}