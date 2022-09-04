package com.vad.starwarssearch.di

import android.content.Context
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.local.CharacterDao
import com.vad.starwarssearch.data.remote.CharacterApi
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.presentation.character.CharacterFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideRoom(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()
}

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkhttp(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): CharacterApi = retrofit.create(CharacterApi::class.java)
}