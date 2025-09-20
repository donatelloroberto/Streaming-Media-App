package com.streamingmediaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.repository.MediaRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val mediaRepository: MediaRepository) : ViewModel() {
    
    private val _favoriteMedia = MutableLiveData<List<MediaItem>>()
    val favoriteMedia: LiveData<List<MediaItem>> = _favoriteMedia
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val favorites = mediaRepository.getFavorites()
                _favoriteMedia.value = favorites
            } catch (e: Exception) {
                _favoriteMedia.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun removeFromFavorites(mediaItem: MediaItem) {
        viewModelScope.launch {
            mediaRepository.toggleFavorite(mediaItem)
            loadFavorites() // Refresh the list
        }
    }
}
