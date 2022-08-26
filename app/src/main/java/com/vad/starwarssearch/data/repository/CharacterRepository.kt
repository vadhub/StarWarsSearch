package com.vad.starwarssearch.data.repository

import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.local.CharacterDao
import com.vad.starwarssearch.data.remote.CharacterApi
import com.vad.starwarssearch.data.remote.RetrofitInstance
import com.vad.starwarssearch.domain.Result
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CharacterRepository
@Inject constructor(
    private val characterDao: CharacterDao,
    private val api: CharacterApi
) {

    suspend fun searchCharacter(name: String): Result {
        val result = api.searchCharacter(name)

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

    suspend fun insert(character: Character) = characterDao.insertFavorite(character)

    suspend fun getSaveCharacter() = characterDao.getAllCharacters()

    suspend fun deleteCharacter(character: Character) = characterDao.deleteCharacter(character)

}