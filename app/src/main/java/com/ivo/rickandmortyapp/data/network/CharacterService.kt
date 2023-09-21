package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.domain.characters.mapper.CharactersResponseToModelDomainMapper
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService
@Inject
constructor(
    private val api:CharacterApiClient,
    private val mapper: CharactersResponseToModelDomainMapper
) {

    suspend fun getAllCharacters(): Flow<DataState<List<CharacterModel>>> = flow {
        emit(DataState.Loading)
        try {
            val charactersFromResponse = api.getAllCharacters()
            val characters = mapper.map(input = charactersFromResponse)
            emit(DataState.Success(characters))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Flow<DataState<CharacterResponse>> = flow {

        emit(DataState.Loading)
        try {
            val characterDetailFromResponse = api.getCharacterDetail(id)
            emit(DataState.Success(characterDetailFromResponse))

        }catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }

    }

    suspend fun getCharactersByPage(page: Int): Flow<DataState<List<CharacterModel>>> = flow  {

        emit(DataState.Loading)
        try {
            val charactersFromResponse = api.getCharactersByPage(page)
            val characters = mapper.map(input = charactersFromResponse)
            emit(DataState.Success(characters))
        }catch (e: Exception){

            e.printStackTrace()
            emit(DataState.Error(e))
        }

    }
}