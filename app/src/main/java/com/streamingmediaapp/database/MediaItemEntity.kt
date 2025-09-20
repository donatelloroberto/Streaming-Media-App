package com.streamingmediaapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.streamingmediaapp.data.MediaItem

@Entity(tableName = "media_items")
data class MediaItemEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val category: String,
    val duration: String,
    val isFavorite: Boolean = false,
    val rating: Float = 0f
) {
    fun toMediaItem(): MediaItem {
        return MediaItem(
            id = id,
            title = title,
            description = description,
            videoUrl = videoUrl,
            thumbnailUrl = thumbnailUrl,
            category = category,
            duration = duration,
            isFavorite = isFavorite,
            rating = rating
        )
    }
    
    companion object {
        fun fromMediaItem(mediaItem: MediaItem): MediaItemEntity {
            return MediaItemEntity(
                id = mediaItem.id,
                title = mediaItem.title,
                description = mediaItem.description,
                videoUrl = mediaItem.videoUrl,
                thumbnailUrl = mediaItem.thumbnailUrl,
                category = mediaItem.category,
                duration = mediaItem.duration,
                isFavorite = mediaItem.isFavorite,
                rating = mediaItem.rating
            )
        }
    }
}
