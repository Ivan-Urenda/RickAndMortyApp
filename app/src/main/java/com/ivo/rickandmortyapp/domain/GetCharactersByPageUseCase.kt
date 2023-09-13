package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.ResponseModel
import javax.inject.Inject

class GetCharactersByPageUseCase @Inject constructor(
    private val repository: CharactersRepository
){
    suspend operator fun invoke(url: String): ResponseModel? = repository.getCharactersByPage(url)
}