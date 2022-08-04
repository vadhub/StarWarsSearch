package com.vad.starwarssearch.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {

        @OptIn(ExperimentalSerializationApi::class)
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            val contentType = "application/json".toMediaType()

            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl("https://swapi.dev/")
                .addConverterFactory(Json.asConverterFactory(contentType))
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(CharacterApi::class.java)
        }
    }
}