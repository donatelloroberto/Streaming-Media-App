package com.streamingmediaapp.repository

import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.database.MediaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepository(private val mediaDao: MediaDao) {
    
    suspend fun getMediaCategories(): List<Pair<String, List<MediaItem>>> = withContext(Dispatchers.IO) {
        // For demo purposes, return sample data
        // In a real app, this would fetch from API and cache in database
        listOf(
            "Featured" to getFeaturedMedia(),
            "Movies" to getMovies(),
            "TV Shows" to getTVShows(),
            "Documentaries" to getDocumentaries(),
            "Favorites" to getFavorites()
        )
    }
    
    suspend fun toggleFavorite(mediaItem: MediaItem) = withContext(Dispatchers.IO) {
        val updatedItem = mediaItem.copy(isFavorite = !mediaItem.isFavorite)
        mediaDao.insertMediaItem(updatedItem)
    }
    
    private fun getFeaturedMedia(): List<MediaItem> {
        return listOf(
            MediaItem(
                id = "1",
                title = "Featured Movie 1",
                description = "An amazing featured movie",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "Featured",
                duration = "2h 15m"
            ),
            MediaItem(
                id = "2",
                title = "Featured Movie 2",
                description = "Another great featured movie",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_2mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "Featured",
                duration = "1h 45m"
            )
        )
    }
    
    private fun getMovies(): List<MediaItem> {
        return listOf(
            MediaItem(
                id = "3",
                title = "Action Movie",
                description = "High-octane action thriller",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "Movies",
                duration = "2h 30m"
            ),
            MediaItem(
                id = "4",
                title = "Comedy Movie",
                description = "Laugh-out-loud comedy",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_2mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "Movies",
                duration = "1h 30m"
            )
        )
    }
    
    private fun getTVShows(): List<MediaItem> {
        return listOf(
            MediaItem(
                id = "5",
                title = "Drama Series",
                description = "Compelling drama series",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "TV Shows",
                duration = "45m"
            )
        )
    }
    
    private fun getDocumentaries(): List<MediaItem> {
        return listOf(
            MediaItem(
                id = "6",
                title = "Nature Documentary",
                description = "Beautiful nature documentary",
                videoUrl = "https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_2mb.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200",
                category = "Documentaries",
                duration = "1h 20m"
            )
        )
    }
    
    private suspend fun getFavorites(): List<MediaItem> = withContext(Dispatchers.IO) {
        mediaDao.getFavoriteMedia()
    }
}
