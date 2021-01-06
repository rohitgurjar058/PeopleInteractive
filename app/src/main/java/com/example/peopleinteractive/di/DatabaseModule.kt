package com.example.peopleinteractive.di

import android.app.Application
import com.example.peopleinteractive.database.Database
import com.example.peopleinteractive.database.DatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Database Module to ask for database instance from dagger
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): Database {
        return Database.getInstance(application.applicationContext)
    }

    @Provides
    fun databaseDao(database: Database): DatabaseDao {
        return database.databaseDao()
    }
}
