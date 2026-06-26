package edu.metrostate.ics342.mediatracker.ui.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.MediaResultsRepository
import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.fakeSearchResults
import edu.metrostate.ics342.mediatracker.data.model.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchResultsViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionRepository: SessionRepository = DefaultSessionRepository(application)
    private val mediaResultsRepository = MediaResultsRepository()
    private var token: String? = ""
    fun getUserToken() {
        viewModelScope.launch {
            token = sessionRepository.getAccessToken()
        }
    }
    private val _results = MutableStateFlow<List<Media>>(emptyList())
    val results: StateFlow<List<Media>> = _results.asStateFlow()

    private val _selectedType = MutableStateFlow("")
    val selectedType: StateFlow<String> = _selectedType.asStateFlow()

    private var currentQuery = ""

    fun search(query: String) {
        currentQuery = query
        viewModelScope.launch {

            token = sessionRepository.getAccessToken()
            Log.d("SearchResultsVM", "Accesstoken in search() : " + token)
            _results.value = mediaResultsRepository.searchMedia(query,token)

        }

        applyFilter()
    }

    fun onTypeSelect(type: String) {
        _selectedType.value = type
        applyFilter()
    }



    private fun applyFilter() {
        val type = _selectedType.value
        _results.value = fakeSearchResults
    }
}