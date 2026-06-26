package edu.metrostate.ics342.mediatracker.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    // TODO (Week 5): Add query StateFlow, results StateFlow, loading/error states.

    private val _popularItems = MutableStateFlow<List<LibraryItem>>(emptyList())
    val popularItems: StateFlow<List<LibraryItem>> = _popularItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _filterState = MutableStateFlow(LibraryStatus.WANT_TO);
    val filterState: StateFlow<LibraryStatus> = _filterState.asStateFlow()

    private val _selectedType = MutableStateFlow("")
    val selectedType: StateFlow<String> = _selectedType.asStateFlow()

    init {
        loadLibrary()
    }

    fun loadLibrary() {
        viewModelScope.launch {
            _isLoading.value = true
            _popularItems.value = FakeMediaRepository.libraryItems
            _isLoading.value = false
        }
    }

    fun removeItem(mediaId: Int) {
        _popularItems.value = _popularItems.value.filter { it.mediaId != mediaId }
    }

    fun updateStatus(mediaId: Int, newStatus: LibraryStatus) {
        _popularItems.value = _popularItems.value.map { item ->
            if (item.mediaId == mediaId) item.copy(status = newStatus) else item
        }
    }

    fun updateFilter(status: LibraryStatus) {
        _filterState.value = status;
    }
    // Wire to GET /media with debounced LaunchedEffect.
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    fun onQueryChange(value: String) { _query.value = value }
    fun clearQuery() { _query.value = "" }
    fun onTypeSelect(type: String) { _selectedType.value = type }
}
