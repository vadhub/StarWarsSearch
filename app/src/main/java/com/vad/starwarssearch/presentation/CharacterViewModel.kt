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
    val characters: MutableLiveData<Resource<ResponseResult>> = MutableLiveData()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        characters.postValue(Resource.Loading())
        var page = 1
        val data = mutableListOf<Character>()

        while (page < 10) {
            val response: Response<ResponseResult> = characterRepository.getPartCharacter(page)
            val resource = handleCharactersResponse(response)

            if (resource !is Resource.Success<*>) {
                characters.postValue(resource)
            }

            resource.data?.let { data.addAll(it.results) }
            characters.postValue(Resource.Success(ResponseResult(data)))
            page++
        }


    }

    private fun handleCharactersResponse(response: Response<ResponseResult>): Resource<ResponseResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveCharacter(character: Character) = viewModelScope.launch {
        characterRepository.upsert(character)
    }

    fun getSaveCharacter() = characterRepository.getSaveCharacter()

    fun deleteCharacter(character: Character) = viewModelScope.launch {
        characterRepository.deleteCharacter(character)
    }
}