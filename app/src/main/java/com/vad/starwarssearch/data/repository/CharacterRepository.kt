package com.vad.starwarssearch.data.repository

import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.remote.RetrofitInstance

class CharacterRepository(val db: AppDatabase) {

    suspend fun getAllCharacter() = RetrofitInstance.api.getAllCharacter()

}