package com.example.peopleinteractive.di

import com.example.peopleinteractive.network.ApiClient
import com.example.peopleinteractive.network.MatchApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Network module to get Retrofit instance from dagger
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient {
        return MatchApi.retrofitClient
    }
}
