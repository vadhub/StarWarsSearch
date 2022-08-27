package com.vad.starwarssearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.repository.CharacterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

open class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    var characters: MutableLiveData<List<Character>> = MutableLiveData()

    fun getCharacters() = viewModelScope.launch {
        characters.postValue(characterRepository.getSaveCharacter())
    }

    fun saveCharacter(character: Character) = viewModelScope.launch {
        characterRepository.insert(character)
    }

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        characterRepository.deleteCharacter(character)
    }
}