package com.example.peopleinteractive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peopleinteractive.models.Match
import com.example.peopleinteractive.repository.Repository
import com.example.peopleinteractive.util.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchViewModel @Inject constructor(private var repository: Repository): ViewModel() {

    /**
     * Fetch matches from db
     */
    val matches = repository.matches

    /**
     * The internal MutableLiveData that stores the status of the most recent request
     */
    private val _status = MutableLiveData<ApiStatus>()

    /**
     * The external immutable LiveData for the request status
     */
    val status: LiveData<ApiStatus>
        get() = _status

    /**
     * Call getMatches() on init so we can display status immediately.
     */

    /**
     * Get the match from network and save to db
     */
    init { getMatches() }

    fun getMatches() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                repository.refreshMatches()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    /**
     * Update Accepted/Declined status of Match to db
     */
    internal fun updateMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateMatch(match) }
    }
}