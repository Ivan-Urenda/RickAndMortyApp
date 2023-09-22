package com.ivo.rickandmortyapp.domain.detail

import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.BaseMapper
import com.ivo.rickandmortyapp.utils.Constants.CHARACTER_ID_DEFAULT
import javax.inject.Inject

class SingleCharacterResponseToModelDomainMapper
@Inject
constructor() : BaseMapper<CharacterResponse, CharacterModel> {
    override fun map(input: CharacterResponse): CharacterModel {
        return CharacterModel(
            id = input.id ?: CHARACTER_ID_DEFAULT,
            name = input.name.orEmpty(),
            image = input.image.orEmpty(),
            specie = input.species.orEmpty(),
            status = input.status.orEmpty(),
            origin = input.origin?.name.orEmpty(),
            location = input.location?.name.orEmpty(),
            episodes = input.episode?.size
        )
    }
}