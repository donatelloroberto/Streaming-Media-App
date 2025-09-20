package com.streamingmediaapp.utils

import org.junit.Test
import org.junit.Assert.*

class MediaUtilsTest {
    
    @Test
    fun testFormatDuration() {
        assertEquals("0:05", MediaUtils.formatDuration(5))
        assertEquals("1:30", MediaUtils.formatDuration(90))
        assertEquals("1:00:00", MediaUtils.formatDuration(3600))
        assertEquals("2:15:30", MediaUtils.formatDuration(8130))
    }
    
    @Test
    fun testFormatFileSize() {
        assertEquals("1.0 KB", MediaUtils.formatFileSize(1024))
        assertEquals("1.0 MB", MediaUtils.formatFileSize(1024 * 1024))
        assertEquals("1.0 GB", MediaUtils.formatFileSize(1024L * 1024 * 1024))
        assertEquals("512 B", MediaUtils.formatFileSize(512))
    }
    
    @Test
    fun testGetVideoQualityFromUrl() {
        assertEquals("1080p", MediaUtils.getVideoQualityFromUrl("video_1080p.mp4"))
        assertEquals("720p", MediaUtils.getVideoQualityFromUrl("video_720p.mp4"))
        assertEquals("480p", MediaUtils.getVideoQualityFromUrl("video_480p.mp4"))
        assertEquals("360p", MediaUtils.getVideoQualityFromUrl("video_360p.mp4"))
        assertEquals("Unknown", MediaUtils.getVideoQualityFromUrl("video.mp4"))
    }
    
    @Test
    fun testIsValidMediaUrl() {
        assertTrue(MediaUtils.isValidMediaUrl("https://example.com/video.mp4"))
        assertTrue(MediaUtils.isValidMediaUrl("http://example.com/video.mp4"))
        assertTrue(MediaUtils.isValidMediaUrl("rtmp://example.com/stream"))
        assertFalse(MediaUtils.isValidMediaUrl("file:///path/to/video.mp4"))
        assertFalse(MediaUtils.isValidMediaUrl("invalid_url"))
    }
    
    @Test
    fun testGenerateThumbnailUrl() {
        val videoUrl = "https://example.com/video.mp4"
        val expectedThumbnailUrl = "https://example.com/video_thumb.jpg"
        assertEquals(expectedThumbnailUrl, MediaUtils.generateThumbnailUrl(videoUrl))
    }
}
