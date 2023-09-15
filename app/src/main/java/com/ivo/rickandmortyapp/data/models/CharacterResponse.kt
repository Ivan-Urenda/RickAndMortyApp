package com.ivo.rickandmortyapp.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("origin")
    val origin: OriginResponse?,
    @SerializedName("location")
    val location: LocationResponse?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("episode")
    val episode: List<String>?
)