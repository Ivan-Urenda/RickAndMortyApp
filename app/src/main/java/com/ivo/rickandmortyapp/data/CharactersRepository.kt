package com.ivo.rickandmortyapp.data

import com.ivo.rickandmortyapp.data.network.CharacterService
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: CharacterService
){

    suspend fun getAllCharacters(): Flow<DataState<List<CharacterModel>>> {
        return api.getAllCharacters()
    }

    suspend fun getCharacter(id: Int): Flow<DataState<CharacterModel>> {
        return api.getCharacterDetail(id)
    }

    suspend fun getCharactersByPage(page: Int): Flow<DataState<List<CharacterModel>>> {
        return api.getCharactersByPage(page)
    }
}