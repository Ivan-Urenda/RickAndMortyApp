package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import javax.inject.Inject

class GetCharactersByPageUseCase @Inject constructor(
    private val repository: CharactersRepository
){
    suspend operator fun invoke(url: String): MainCharactersResponse? = repository.getCharactersByPage(url)
}