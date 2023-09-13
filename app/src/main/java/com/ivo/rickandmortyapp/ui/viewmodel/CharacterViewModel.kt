package com.ivo.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivo.rickandmortyapp.data.models.ResponseModel
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.domain.GetAllCharactersUseCase
import com.ivo.rickandmortyapp.domain.GetCharacterUseCase
import com.ivo.rickandmortyapp.domain.GetCharactersByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersByPageUseCase: GetCharactersByPageUseCase
): ViewModel(){

    val responseModel = MutableLiveData<ResponseModel?>()
    val resultsModel = MutableLiveData<ResultsModel?>()
    val responseModelByPage = MutableLiveData<ResponseModel?>()

    fun getAllCharacters() {

        viewModelScope.launch {
            val result = getAllCharactersUseCase()
            responseModel.postValue(result)
        }
    }

    fun getCharacter(url: String) {

        viewModelScope.launch {
            val result = getCharacterUseCase(url)
            resultsModel.postValue(result)
        }
    }

    fun getCharactersByPage(url: String) {

        viewModelScope.launch {
            val result = getCharactersByPageUseCase(url)
            responseModelByPage.postValue(result)
        }
    }
}