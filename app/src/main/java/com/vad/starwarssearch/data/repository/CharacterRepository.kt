package com.vad.starwarssearch.data.repository

import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.local.CharacterDao
import com.vad.starwarssearch.data.remote.RetrofitInstance
import com.vad.starwarssearch.domain.Result
import java.lang.IllegalArgumentException

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun searchCharacter(name:String): Result {
        val result = RetrofitInstance.apiCreate().searchCharacter(name)

        return if (result.isSuccessful) {
            if (result.body() != null) {
                Result.Success(result.body()!!.listCharacters)
            } else {
                Result.Error("not elements")
            }
        } else {
            Result.Error(result.message())
        }
    }

    suspend fun upsert(character: Character) = characterDao.insertFavorite(character)

    fun getSaveCharacter() = characterDao.getAllCharacters()

    suspend fun deleteCharacter(character: Character) = characterDao.deleteCharacter(character)

}