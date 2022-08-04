package com.vad.starwarssearch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vad.starwarssearch.data.entity.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {
    @Query("SELECT * FROM character_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): CharacterRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM character_remote_keys")
    suspend fun deleteAllREmoteKeys()
}