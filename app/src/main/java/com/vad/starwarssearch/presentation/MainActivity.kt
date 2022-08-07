package com.vad.starwarssearch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vad.starwarssearch.App
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.starWarsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}