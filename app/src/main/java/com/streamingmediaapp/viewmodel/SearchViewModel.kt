package com.streamingmediaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.repository.MediaRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val mediaRepository: MediaRepository) : ViewModel() {
    
    private val _searchResults = MutableLiveData<List<MediaItem>>()
    val searchResults: LiveData<List<MediaItem>> = _searchResults
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    fun searchMedia(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val results = mediaRepository.searchMedia(query)
                _searchResults.value = results
            } catch (e: Exception) {
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
