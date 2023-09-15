package com.ivo.rickandmortyapp.data

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.data.network.CharacterService
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: CharacterService
){

    suspend fun getAllCharacters(): Flow<List<CharacterModel>> {
        return api.getAllCharacters()
    }

    suspend fun getCharacter(url: String): CharacterResponse? {
        return api.getCharacter(url)
    }

    suspend fun getCharactersByPage(url: String): MainCharactersResponse? {
        return api.getCharactersByPage(url)
    }
}