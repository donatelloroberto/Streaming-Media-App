package com.streamingmediaapp

import com.streamingmediaapp.data.MediaItem
import org.junit.Test
import org.junit.Assert.*

class MediaItemTest {
    
    @Test
    fun testMediaItemCreation() {
        val mediaItem = MediaItem(
            id = "1",
            title = "Test Movie",
            description = "Test Description",
            videoUrl = "https://example.com/video.mp4",
            thumbnailUrl = "https://example.com/thumb.jpg",
            category = "Movies",
            duration = "2h 30m",
            isFavorite = false,
            rating = 4.5f
        )
        
        assertEquals("1", mediaItem.id)
        assertEquals("Test Movie", mediaItem.title)
        assertEquals("Test Description", mediaItem.description)
        assertEquals("https://example.com/video.mp4", mediaItem.videoUrl)
        assertEquals("https://example.com/thumb.jpg", mediaItem.thumbnailUrl)
        assertEquals("Movies", mediaItem.category)
        assertEquals("2h 30m", mediaItem.duration)
        assertFalse(mediaItem.isFavorite)
        assertEquals(4.5f, mediaItem.rating, 0.1f)
    }
    
    @Test
    fun testMediaItemParcelable() {
        val originalItem = MediaItem(
            id = "2",
            title = "Parcelable Test",
            description = "Testing parcelable functionality",
            videoUrl = "https://example.com/test.mp4",
            thumbnailUrl = "https://example.com/test_thumb.jpg",
            category = "TV Shows",
            duration = "45m",
            isFavorite = true,
            rating = 3.8f
        )
        
        // Test that the item can be written to and read from a parcel
        val parcel = android.os.Parcel.obtain()
        originalItem.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        
        val recreatedItem = MediaItem.CREATOR.createFromParcel(parcel)
        parcel.recycle()
        
        assertEquals(originalItem.id, recreatedItem.id)
        assertEquals(originalItem.title, recreatedItem.title)
        assertEquals(originalItem.description, recreatedItem.description)
        assertEquals(originalItem.videoUrl, recreatedItem.videoUrl)
        assertEquals(originalItem.thumbnailUrl, recreatedItem.thumbnailUrl)
        assertEquals(originalItem.category, recreatedItem.category)
        assertEquals(originalItem.duration, recreatedItem.duration)
        assertEquals(originalItem.isFavorite, recreatedItem.isFavorite)
        assertEquals(originalItem.rating, recreatedItem.rating, 0.1f)
    }
}
