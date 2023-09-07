package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApiClient {
    @GET("character")
    suspend fun getAllCharacters():Response<ResponseModel>

    @GET
    suspend fun getCharacter(@Url url: String):Response<ResultsModel>

    @GET
    suspend fun getCharactersByPage(@Url url: String):Response<ResponseModel>
}