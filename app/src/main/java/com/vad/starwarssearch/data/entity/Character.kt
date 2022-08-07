package com.vad.starwarssearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import java.io.Serializable

@Entity(tableName = "character")
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
): Serializable