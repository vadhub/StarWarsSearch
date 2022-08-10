package com.vad.starwarssearch.data.local

import androidx.room.*
import com.vad.starwarssearch.data.entity.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : MutableList<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)
}