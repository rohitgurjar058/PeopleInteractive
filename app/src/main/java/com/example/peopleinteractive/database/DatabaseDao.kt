package com.example.peopleinteractive.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.peopleinteractive.models.Match

/**
 * Defines methods for using the Model class with Room.
 */
@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matches: List<Match>)

    @Query("select * from matches")
    fun getMatches(): LiveData<List<Match>>

    @Update
    suspend fun updateMatch(match: Match)
}
