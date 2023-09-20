package com.ivo.rickandmortyapp.data.network

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterApiClient {
    @GET("character")
    suspend fun getAllCharacters(): MainCharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): CharacterResponse

    @GET
    suspend fun getCharactersByPage(@Url page: String):Response<MainCharactersResponse>
}