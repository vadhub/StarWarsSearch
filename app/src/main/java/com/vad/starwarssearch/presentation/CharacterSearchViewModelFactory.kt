package com.vad.starwarssearch.presentation

import androidx.lifecycle.ViewModel
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.HandleError

class CharacterSearchViewModelFactory(
    private val characterRepository: CharacterRepository,
    private val handleError: HandleError
) : CharacterViewModelFactory(characterRepository) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterSearchViewModel(characterRepository, handleError) as T
    }
}