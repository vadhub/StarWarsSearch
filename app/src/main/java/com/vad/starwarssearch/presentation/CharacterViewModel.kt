package com.vad.starwarssearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Characters
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val characters: MutableLiveData<Resource<List<Characters>>> = MutableLiveData()
    val characterPage = 1

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        characters.postValue(Resource.Loading())
        val response = characterRepository.getAllCharacter()
        characters.postValue(handleCharactersResponse(response))
    }

    private fun handleCharactersResponse(response: Response<List<Characters>>): Resource<List<Characters>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}