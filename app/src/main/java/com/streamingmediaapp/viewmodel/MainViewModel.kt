package com.streamingmediaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.repository.MediaRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mediaRepository: MediaRepository) : ViewModel() {
    
    private val _mediaCategories = MutableLiveData<List<Pair<String, List<MediaItem>>>>()
    val mediaCategories: LiveData<List<Pair<String, List<MediaItem>>>> = _mediaCategories
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    fun loadMediaContent() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val categories = mediaRepository.getMediaCategories()
                _mediaCategories.value = categories
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun refreshContent() {
        loadMediaContent()
    }
    
    fun toggleFavorite(mediaItem: MediaItem) {
        viewModelScope.launch {
            mediaRepository.toggleFavorite(mediaItem)
            loadMediaContent() // Refresh to show updated state
        }
    }
}
