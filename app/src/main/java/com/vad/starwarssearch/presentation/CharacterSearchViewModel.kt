package com.vad.starwarssearch.presentation

import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.HandleResult
import com.vad.starwarssearch.domain.HandleError
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val characterRepository: CharacterRepository,
    private val handleError: HandleError
) : CharacterViewModel(characterRepository), HandleResult {

    fun searchCharacters(name: String) = viewModelScope.launch {
        characterRepository.searchCharacter(name).handle(this@CharacterSearchViewModel)
    }

    override fun handleSuccess(list: List<Character>) {
        characters.postValue(list)
    }

    override fun handleError(error: String) {
        handleError.handle(error)
    }
}