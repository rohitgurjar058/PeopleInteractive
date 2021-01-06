package com.example.peopleinteractive.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peopleinteractive.models.Match

/**
 * A database that stores Match information.
 */
@androidx.room.Database(entities = [Match::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    /**
     * Connects the database to the DAO.
     */
    abstract fun databaseDao() : DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            synchronized(this) {
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            Database::class.java,
                            "database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance;
                return instance
            }
        }
    }
}
