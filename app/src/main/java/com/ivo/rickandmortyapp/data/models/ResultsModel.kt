package com.ivo.rickandmortyapp.data.models

data class ResultsModel(val id:Int, val name:String, val species:String, var status:String, val origin: OriginModel, val location: LocationModel, val image: String)