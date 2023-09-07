package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.core.RetrofitHelper
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllCharacters(): ResponseModel? {

        try {
            return withContext(Dispatchers.IO) {
                val response = retrofit.create(CharacterApiClient::class.java).getAllCharacters()
                response.body()
            }
        }catch (e: Exception)
        {
            return null
        }

    }

    suspend fun getCharacter(url: String): ResultsModel? {

        try {
            return withContext(Dispatchers.IO) {
                val response = retrofit.create(CharacterApiClient::class.java).getCharacter(url)
                response.body()!!
            }
        }catch (e: Exception){
            return null
        }

    }

    suspend fun getCharactersByPage(url: String): ResponseModel? {

        try {
            return withContext(Dispatchers.IO) {
                val response = retrofit.create(CharacterApiClient::class.java).getCharactersByPage(url)
                response.body()
            }
        }catch (e: Exception){

            return null
        }

    }
}