package com.vad.starwarssearch.data.remote

import com.vad.starwarssearch.data.entity.CharactersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/people")
    suspend fun searchCharacter(@Query("search") characterName: String) : Response<CharactersList>
}