package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersByPageUseCase @Inject constructor(
    private val repository: CharactersRepository
){
    suspend operator fun invoke(page: Int): Flow<DataState<List<CharacterModel>>> = repository.getCharactersByPage(page)
}