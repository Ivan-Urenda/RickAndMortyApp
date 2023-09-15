package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApiClient {
    @GET("character")
    suspend fun getAllCharacters(): MainCharactersResponse

    @GET
    suspend fun getCharacterDetail(@Url url: Int):Response<CharacterResponse>

    @GET
    suspend fun getCharactersByPage(@Url page: String):Response<MainCharactersResponse>
}