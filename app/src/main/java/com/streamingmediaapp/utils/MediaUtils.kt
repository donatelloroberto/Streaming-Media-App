package com.streamingmediaapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.streamingmediaapp.data.MediaItem
import java.text.SimpleDateFormat
import java.util.*

object MediaUtils {
    
    fun formatDuration(durationInSeconds: Long): String {
        val hours = durationInSeconds / 3600
        val minutes = (durationInSeconds % 3600) / 60
        val seconds = durationInSeconds % 60
        
        return when {
            hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
            else -> String.format("%d:%02d", minutes, seconds)
        }
    }
    
    fun formatFileSize(bytes: Long): String {
        val kb = bytes / 1024.0
        val mb = kb / 1024.0
        val gb = mb / 1024.0
        
        return when {
            gb >= 1 -> String.format("%.1f GB", gb)
            mb >= 1 -> String.format("%.1f MB", mb)
            kb >= 1 -> String.format("%.1f KB", kb)
            else -> "$bytes B"
        }
    }
    
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected == true
        }
    }
    
    fun getVideoQualityFromUrl(url: String): String {
        return when {
            url.contains("1080p") -> "1080p"
            url.contains("720p") -> "720p"
            url.contains("480p") -> "480p"
            url.contains("360p") -> "360p"
            else -> "Unknown"
        }
    }
    
    fun isValidMediaUrl(url: String): Boolean {
        return url.startsWith("http://") || url.startsWith("https://") || url.startsWith("rtmp://")
    }
    
    fun generateThumbnailUrl(videoUrl: String): String {
        // This would typically call a thumbnail generation service
        return videoUrl.replace(".mp4", "_thumb.jpg")
    }
    
    fun formatDate(timestamp: Long): String {
        val date = Date(timestamp)
        val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return formatter.format(date)
    }
    
    fun getCategoryIcon(category: String): Int {
        return when (category.lowercase()) {
            "movies" -> com.streamingmediaapp.R.drawable.ic_movie
            "tv shows" -> com.streamingmediaapp.R.drawable.ic_tv
            "documentaries" -> com.streamingmediaapp.R.drawable.ic_documentary
            "music" -> com.streamingmediaapp.R.drawable.ic_music
            else -> com.streamingmediaapp.R.drawable.ic_play_arrow
        }
    }
}
