package com.vad.starwarssearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.entity.CharactersList
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.HandleResult
import com.vad.starwarssearch.domain.Result
import com.vad.starwarssearch.presentation.character.HandleError
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(private val characterRepository: CharacterRepository, private val handleError: HandleError) : ViewModel(), HandleResult {
    val characters: MutableLiveData<List<Character>> = MutableLiveData()

    fun searchCharacters(name: String) = viewModelScope.launch {
        characterRepository.searchCharacter(name)
    }

    fun saveCharacter(character: Character) = viewModelScope.launch {
        characterRepository.upsert(character)
    }

    fun getSaveCharacter() = characterRepository.getSaveCharacter()

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        characterRepository.deleteCharacter(character)
    }

    override fun handleSuccess(list: List<Character>) {
        characters.postValue(list)
    }

    override fun handleError(error: String) {
        handleError.handle(error)
    }
}