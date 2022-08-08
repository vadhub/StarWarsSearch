package com.vad.starwarssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.presentation.character.HandleError

class CharacterViewModelFactory(private val characterRepository: CharacterRepository, private val handleError: HandleError) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(characterRepository, handleError) as T
    }
}