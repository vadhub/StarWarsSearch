package com.vad.starwarssearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vad.starwarssearch.data.entity.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAllCharacters() : LiveData<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)
}