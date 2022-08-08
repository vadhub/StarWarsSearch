package com.vad.starwarssearch

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.CharacterViewModelFactory

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
