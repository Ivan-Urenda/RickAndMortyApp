package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.ResultsModel
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
){


    suspend operator fun invoke(url: String): ResultsModel? = repository.getCharacter(url)
}