package com.ivo.rickandmortyapp.domain

import com.ivo.rickandmortyapp.data.CharactersRepository
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
){


    suspend operator fun invoke(id: Int): Flow<DataState<CharacterModel>> = repository.getCharacter(id)
}