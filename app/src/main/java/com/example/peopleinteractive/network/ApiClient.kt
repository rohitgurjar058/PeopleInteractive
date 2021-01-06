package com.example.peopleinteractive.network

import com.example.peopleinteractive.models.Match
import com.example.peopleinteractive.models.Model
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://randomuser.me/"

/**
 * Build the Moshi object that Retrofit will be using
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Set Logging Interceptor
 */
val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

/**
 * Build Http Client for Retrofit
 */
val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiClient {
    /**
     * Returns [List] of [Match] which can be fetched in a Coroutine scope.
     */
    @GET("/api/")
    suspend fun getMatches(@Query("results") results: Int): Model
}

/**
 * Api that exposes the lazy-initialized Retrofit Client
 */
object MatchApi {
    val retrofitClient : ApiClient by lazy { retrofit.create(ApiClient::class.java) }
}