package com.vad.starwarssearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vad.starwarssearch.data.entity.Characters

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAllCharacters() : LiveData<List<Characters>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characters: Characters)

    @Delete
    suspend fun deleteCharacter(characters: Characters)
}