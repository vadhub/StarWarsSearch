package com.vad.starwarssearch.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://swapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun apiCreate(): CharacterApi = retrofit.create(CharacterApi::class.java)

}