package com.vad.starwarssearch

import android.app.Application
import androidx.room.Room
import com.vad.starwarssearch.data.local.AppDatabase

class App : Application(), ProvideDB {

    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "characters.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun provide(): AppDatabase = db
}

interface ProvideDB {
    fun provide(): AppDatabase
}
