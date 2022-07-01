package com.vad.starwarssearch.data.remote

import com.vad.starwarssearch.data.entity.Character
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {
    @GET("api/people")
    suspend fun getAllCharacter() : Response<List<Character>>
}