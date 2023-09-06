package com.ivo.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.domain.GetCharacters
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    val responseModel = MutableLiveData<ResponseModel>()

    fun onCreate() {

        viewModelScope.launch {
            val result = GetCharacters().characters()
            responseModel.postValue(result)
        }
    }
}