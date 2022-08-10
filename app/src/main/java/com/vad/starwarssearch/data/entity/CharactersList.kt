package com.vad.starwarssearch.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersList(
    @SerializedName("results")
    @Expose
    val listCharacters: List<Character>
)