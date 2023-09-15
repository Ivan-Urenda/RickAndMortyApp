package com.ivo.rickandmortyapp.data.models

import com.google.gson.annotations.SerializedName

data class LocationResponse (
    @SerializedName("name")
    val name:String?,
    @SerializedName("url")
    val url:String?
)