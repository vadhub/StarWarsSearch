package com.vad.starwarssearch.data.local

import android.content.Context
import androidx.room.*
import com.vad.starwarssearch.data.entity.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {instance = it}
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "characters.db")
                .fallbackToDestructiveMigration()
                .build()

    }
}