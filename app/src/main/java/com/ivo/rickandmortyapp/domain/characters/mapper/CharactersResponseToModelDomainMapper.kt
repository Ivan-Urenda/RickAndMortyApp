package com.ivo.rickandmortyapp.domain.characters.mapper

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.BaseMapper
import javax.inject.Inject

class CharactersResponseToModelDomainMapper
@Inject
constructor(): BaseMapper<MainCharactersResponse, List<CharacterModel>> {

    override fun map(input: MainCharactersResponse): List<CharacterModel> {

        return input.characters?.let { charactersResponses ->
            charactersResponses.map { singleCharacter ->
                with(singleCharacter) {
                    CharacterModel(
                        name = name.orEmpty(),
                        specie = species.orEmpty(),
                        status = status.orEmpty(),
                        origin = origin?.name.orEmpty(),
                        location = location?.name.orEmpty()
                    )
                }
            }

        } ?: emptyList()

    }
}