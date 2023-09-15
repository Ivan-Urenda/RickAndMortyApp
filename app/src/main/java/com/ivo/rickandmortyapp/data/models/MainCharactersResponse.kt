package com.ivo.rickandmortyapp.data.models

import com.google.gson.annotations.SerializedName

data class MainCharactersResponse(
    @SerializedName("info")
    val info: InfoResponse?,
    @SerializedName("results")
    val characters: List<CharacterResponse>?
)