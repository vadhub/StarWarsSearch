package com.vad.starwarssearch.data.local

import androidx.room.*
import com.vad.starwarssearch.data.entity.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}