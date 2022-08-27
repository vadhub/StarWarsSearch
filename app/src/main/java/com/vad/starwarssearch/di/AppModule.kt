package com.vad.starwarssearch.di

import android.content.Context
import com.vad.starwarssearch.data.local.AppDatabase
import com.vad.starwarssearch.data.local.CharacterDao
import com.vad.starwarssearch.data.remote.CharacterApi
import com.vad.starwarssearch.presentation.character.CharacterFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(characterFragment: CharacterFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}

@Module(includes = [NetworkModule::class, RoomModule::class])
class AppModule

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()
}

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkhttp(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): CharacterApi = retrofit.create(CharacterApi::class.java)
}