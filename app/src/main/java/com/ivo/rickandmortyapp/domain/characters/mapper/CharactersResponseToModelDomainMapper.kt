package com.ivo.rickandmortyapp.domain.characters.mapper

import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.BaseMapper
import com.ivo.rickandmortyapp.utils.Constants.CHARACTER_ID_DEFAULT
import javax.inject.Inject

class CharactersResponseToModelDomainMapper
@Inject
constructor(): BaseMapper<MainCharactersResponse, List<CharacterModel>> {

    override fun map(input: MainCharactersResponse): List<CharacterModel> {
        return input.characters?.let { charactersResponses ->
            charactersResponses.map { singleCharacter ->
                with(singleCharacter) {
                    CharacterModel(
                        id = id ?: CHARACTER_ID_DEFAULT,
                        name = name.orEmpty(),
                        image = image.orEmpty(),
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
