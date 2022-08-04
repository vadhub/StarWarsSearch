package com.vad.starwarssearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "character")
@Serializable
data class Character(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @SerialName("name")
        val name: String,

        @SerialName("birth_year")
        val birthYear: String,

        @SerialName("gender")
        val gender: String,

        @SerialName("films")
        val films: List<String>,
)