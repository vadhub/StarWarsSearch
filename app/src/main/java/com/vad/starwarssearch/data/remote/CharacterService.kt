package com.vad.starwarssearch.data.remote

import androidx.lifecycle.LiveData
import com.vad.starwarssearch.data.entity.Character
import retrofit.http.GET

interface CharacterService {
    @GET("api/people")
    suspend fun getAllCharacter() : LiveData<List<Character>>
}