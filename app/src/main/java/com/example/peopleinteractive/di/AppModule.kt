package com.example.peopleinteractive.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Get the Application instance from dagger
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }
}