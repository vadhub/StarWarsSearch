package com.vad.starwarssearch

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.CharacterViewModelFactory

class App(private val viewModelStoreOwner: ViewModelStoreOwner) : Application() {

    lateinit var db: AppDatabase
    lateinit var viewModel: CharacterViewModel

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "characters.db")
            .fallbackToDestructiveMigration()
            .build()

        val characterRepository = CharacterRepository(db.characterDao())
        val viewModelFactory = CharacterViewModelFactory(characterRepository)
        viewModel = ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(CharacterViewModel::class.java)
    }

}