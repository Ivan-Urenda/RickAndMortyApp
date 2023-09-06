package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.data.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiClient {
    @GET("character")
    suspend fun getAllCharacters():Response<ResponseModel>
}