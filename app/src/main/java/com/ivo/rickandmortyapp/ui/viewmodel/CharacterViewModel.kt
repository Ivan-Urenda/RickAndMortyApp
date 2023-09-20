package com.ivo.rickandmortyapp.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivo.rickandmortyapp.data.models.MainCharactersResponse
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.domain.GetAllCharactersUseCase
import com.ivo.rickandmortyapp.domain.GetCharacterUseCase
import com.ivo.rickandmortyapp.domain.GetCharactersByPageUseCase
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersByPageUseCase: GetCharactersByPageUseCase
): ViewModel(){

    val mainCharactersResponse = MutableLiveData<MainCharactersResponse?>()

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError


    val characterResponse = MutableLiveData<CharacterResponse?>()
    val mainCharactersResponseByPage = MutableLiveData<MainCharactersResponse?>()

    @SuppressLint("NullSafeMutableLiveData")
    fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase()
                .catch {
                    it.printStackTrace()
                    _isError.value = false
                }
                .collect { dataState ->
                    when (dataState) {
                        DataState.Loading -> _loading.value = true
                        is DataState.Error -> {
                            _loading.value = false
                            _isError.value = true
                        }

                        is DataState.Success -> {
                            _loading.value = false
                            _characters.value = dataState.data
                        }
                    }
                }
        }
    }

    fun getCharacter(url: String) {

        viewModelScope.launch {
            val result = getCharacterUseCase(url)
            characterResponse.postValue(result)
        }
    }

    fun getCharactersByPage(url: String) {

        viewModelScope.launch {
            val result = getCharactersByPageUseCase(url)
            mainCharactersResponseByPage.postValue(result)
        }
    }

}
