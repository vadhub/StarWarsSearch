package com.vad.starwarssearch.di

import com.vad.starwarssearch.data.remote.CharacterApi
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Component(modules = [AppModule::class])
interface AppComponent

@Module
class AppModule {

    @Provides
    fun provideCharacterApi(): CharacterApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

}