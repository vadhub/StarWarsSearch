package com.vad.starwarssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.repository.CharacterRepository
import kotlinx.coroutines.launch

open class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun saveCharacter(character: Character) = viewModelScope.launch {
        characterRepository.upsert(character)
    }

    fun getSaveCharacter() = characterRepository.getSaveCharacter()

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        characterRepository.deleteCharacter(character)
    }
}