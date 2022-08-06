package com.vad.starwarssearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.entity.ResponseResult
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.domain.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val characters: MutableLiveData<List<Character>> = MutableLiveData()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        val data = mutableListOf<Character>()
        val response: Response<ResponseResult> = characterRepository.getPartCharacter(1)

    }

    fun saveCharacter(character: Character) = viewModelScope.launch {
        characterRepository.upsert(character)
    }

    fun getSaveCharacter() = characterRepository.getSaveCharacter()

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        characterRepository.deleteCharacter(character)
    }
}