package com.example.peopleinteractive.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.peopleinteractive.database.Database
import com.example.peopleinteractive.models.Match
import com.example.peopleinteractive.models.Model
import com.example.peopleinteractive.models.asDomainModel
import com.example.peopleinteractive.network.MatchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository to fetch from network and storing them on disk
 */
@Singleton
class Repository @Inject constructor(private val database: Database) {

    private val matchList = mutableListOf<Match>()
    val matches: LiveData<List<Match>> = Transformations.map(database.databaseDao().getMatches()) {
        it.asDomainModel()
    }

    /**
     *   Sync database data with the Network in background
     */
    suspend fun refreshMatches()  {
        withContext(Dispatchers.IO) {
            val matches = MatchApi.retrofitClient.getMatches(10)
            saveToDatabase(matches)
        }
    }

    /**
     * Save Match data to db
     */
    private suspend fun saveToDatabase(matches: Model) {
        for (result in matches.results)
            matchList.add(Match(id = result.id.name , imgSrcUrl = result.picture.medium,
                    isAccepted = false, isDeclined = false))
        database.databaseDao().insertAll(matchList)
    }

    /**
     * Update Accept/Declined Status
     */
    suspend fun updateMatch(match: Match) {
        database.databaseDao().updateMatch(match)
    }
}
