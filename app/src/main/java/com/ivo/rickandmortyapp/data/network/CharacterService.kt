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

    suspend fun getCharacter(url: String): CharacterResponse? {

        /*try {
            return withContext(Dispatchers.IO) {
                val response = api.getCharacter(url)
                response.body()!!
            }
        }catch (e: Exception){
            return null
        }*/

        return null

    }

    suspend fun getCharactersByPage(url: String): MainCharactersResponse? {

        try {
            return withContext(Dispatchers.IO) {
                val response = api.getCharactersByPage(url)
                response.body()
            }
        }catch (e: Exception){

            return null
        }

    }
}