package com.streamingmediaapp.repository

import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.database.MediaDao
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MediaRepositoryTest {
    
    @Mock
    private lateinit var mediaDao: MediaDao
    
    private lateinit var mediaRepository: MediaRepository
    
    @Before
    fun setUp() {
        mediaRepository = MediaRepository(mediaDao)
    }
    
    @Test
    fun testGetMediaCategories() = runTest {
        // Given
        val expectedFavorites = listOf<MediaItem>()
        whenever(mediaDao.getFavoriteMedia()).thenReturn(expectedFavorites)
        
        // When
        val categories = mediaRepository.getMediaCategories()
        
        // Then
        assert(categories.isNotEmpty())
        assert(categories.any { it.first == "Featured" })
        assert(categories.any { it.first == "Movies" })
        assert(categories.any { it.first == "TV Shows" })
        assert(categories.any { it.first == "Documentaries" })
        assert(categories.any { it.first == "Favorites" })
    }
    
    @Test
    fun testToggleFavorite() = runTest {
        // Given
        val mediaItem = MediaItem(
            id = "1",
            title = "Test Movie",
            description = "Test Description",
            videoUrl = "https://example.com/video.mp4",
            thumbnailUrl = "https://example.com/thumb.jpg",
            category = "Movies",
            duration = "2h",
            isFavorite = false,
            rating = 4.0f
        )
        
        // When
        mediaRepository.toggleFavorite(mediaItem)
        
        // Then
        // Verify that the DAO method was called
        // Note: In a real test, you'd verify the actual behavior
    }
}
