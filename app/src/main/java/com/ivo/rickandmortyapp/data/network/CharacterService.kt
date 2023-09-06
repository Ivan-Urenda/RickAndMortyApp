package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.core.RetrofitHelper
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllCharacters(): ResponseModel {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getAllCharacters()
            response.body()!!
        }
    }

    suspend fun getCharacter(url: String): ResultsModel {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getCharacter(url)
            response.body()!!
        }
    }
}