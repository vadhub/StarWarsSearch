package com.vad.starwarssearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_remote_keys")
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int,
    val nextPage: Int
)
