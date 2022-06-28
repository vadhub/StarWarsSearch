package com.vad.starwarssearch.data.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Character(
        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("height")
        @Expose
        val height: String,

        @SerializedName("mass")
        @Expose
        val mass: String,

        @SerializedName("hair_color")
        @Expose
        val hairColor: String,

        @SerializedName("skin_color")
        @Expose
        val skinColor: String,

        @SerializedName("eye_color")
        @Expose
        val eyeColor: String,

        @SerializedName("birth_year")
        @Expose
        val birthYear: String,

        @SerializedName("gender")
        @Expose
        val gender: String,

        @SerializedName("homeworld")
        @Expose
        val homeworld: String,

        @SerializedName("films")
        @Expose
        val films: List<String>,

        @SerializedName("species")
        @Expose
        val species: List<String>,

        @SerializedName("vehicles")
        @Expose
        val vehicles: List<String>,

        @SerializedName("starships")
        @Expose
        val starships: List<String>,

        @SerializedName("created")
        @Expose
        val created: String,

        @SerializedName("edited")
        @Expose
        val edited: String,

        @SerializedName("url")
        @Expose
        val url: String
)