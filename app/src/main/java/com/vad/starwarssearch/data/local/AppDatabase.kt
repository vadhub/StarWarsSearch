package com.vad.starwarssearch.data.local

import android.content.Context
import androidx.room.*
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.data.entity.CharacterRemoteKeys

@Database(entities = [Character::class, CharacterRemoteKeys::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {instance = it}
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext.applicationContext, AppDatabase::class.java, "characters.db")
                .fallbackToDestructiveMigration()
                .build()

    }
}