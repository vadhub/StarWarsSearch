package com.vad.starwarssearch.data.repository

import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.remote.RetrofitInstance

class CharacterRepository(val db: AppDatabase) {

    suspend fun getAllCharacter() = RetrofitInstance.api.getAllCharacter()

    suspend fun upsert(character: Character) = db.characterDao().insertCharacter(character)

    fun getSaveCharacter() = db.characterDao().getAllCharacters()

    suspend fun deleteCharacter(character: Character) = db.characterDao().deleteCharacter(character)

}