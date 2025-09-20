package com.streamingmediaapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaItem(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val category: String,
    val duration: String,
    val isFavorite: Boolean = false,
    val rating: Float = 0f
) : Parcelable
