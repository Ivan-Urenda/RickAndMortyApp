package com.ivo.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.domain.GetAllCharacters
import com.ivo.rickandmortyapp.domain.GetCharacter
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    val responseModel = MutableLiveData<ResponseModel>()
    val resultsModel = MutableLiveData<ResultsModel>()

    fun getAllCharacters() {

        viewModelScope.launch {
            val result = GetAllCharacters().characters()
            responseModel.postValue(result)
        }
    }

    fun getCharacter(url: String) {

        viewModelScope.launch {
            val result = GetCharacter().character(url)
            resultsModel.postValue(result)
        }
    }
}