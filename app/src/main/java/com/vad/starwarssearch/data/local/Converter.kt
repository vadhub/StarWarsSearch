package com.vad.starwarssearch.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun fromList(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun toList(string: String): List<String> = Gson().fromJson(string, Array<String>::class.java).toList()
}