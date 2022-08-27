package com.vad.starwarssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.HandleError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class CharacterSearchViewModelFactory @AssistedInject constructor(
    private val characterRepository: CharacterRepository,
    @Assisted("handleError") private val handleError: HandleError
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterSearchViewModel(characterRepository, handleError) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("handleError") handleError: HandleError): CharacterSearchViewModelFactory
    }
}