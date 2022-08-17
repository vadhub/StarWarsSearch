package com.vad.starwarssearch.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "character")
data class Character(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("birth_year")
        @Expose
        val birthYear: String,

        @SerializedName("gender")
        @Expose
        val gender: String,

        @SerializedName("films")
        @Expose
        val films: List<String>,

        var isFavorite: Boolean
): Serializable {
        fun setFavorite() {
                isFavorite = true
        }
}