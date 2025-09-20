package com.streamingmediaapp.utils

object Constants {
    
    // API Constants
    const val BASE_URL = "https://api.streamingmediaapp.com/"
    const val API_TIMEOUT = 30L
    
    // Database Constants
    const val DATABASE_NAME = "streaming_media_database"
    const val DATABASE_VERSION = 1
    
    // SharedPreferences Keys
    const val PREFS_NAME = "streaming_media_prefs"
    const val KEY_VIDEO_QUALITY = "video_quality"
    const val KEY_AUDIO_LANGUAGE = "audio_language"
    const val KEY_SUBTITLES_ENABLED = "subtitles_enabled"
    const val KEY_PARENTAL_CONTROLS_ENABLED = "parental_controls_enabled"
    const val KEY_MAX_CONTENT_RATING = "max_content_rating"
    const val KEY_AUTO_PLAY = "auto_play"
    const val KEY_LAST_PLAYED_POSITION = "last_played_position_"
    
    // Notification Constants
    const val NOTIFICATION_CHANNEL_ID = "media_playback_channel"
    const val NOTIFICATION_ID = 1
    
    // Media Session Actions
    const val ACTION_PLAY = "com.streamingmediaapp.ACTION_PLAY"
    const val ACTION_PAUSE = "com.streamingmediaapp.ACTION_PAUSE"
    const val ACTION_STOP = "com.streamingmediaapp.ACTION_STOP"
    const val ACTION_NEXT = "com.streamingmediaapp.ACTION_NEXT"
    const val ACTION_PREVIOUS = "com.streamingmediaapp.ACTION_PREVIOUS"
    
    // Intent Extras
    const val EXTRA_MEDIA_ITEM = "media_item"
    const val EXTRA_PLAYBACK_POSITION = "playback_position"
    const val EXTRA_IS_PLAYING = "is_playing"
    
    // Video Quality Options
    const val QUALITY_AUTO = "auto"
    const val QUALITY_1080P = "1080p"
    const val QUALITY_720P = "720p"
    const val QUALITY_480P = "480p"
    const val QUALITY_360P = "360p"
    
    // Content Ratings
    const val RATING_G = "G"
    const val RATING_PG = "PG"
    const val RATING_PG13 = "PG-13"
    const val RATING_R = "R"
    const val RATING_NC17 = "NC-17"
    
    // Default Values
    const val DEFAULT_VIDEO_QUALITY = QUALITY_AUTO
    const val DEFAULT_AUDIO_LANGUAGE = "en"
    const val DEFAULT_MAX_CONTENT_RATING = RATING_PG13
    
    // Cache Settings
    const val CACHE_SIZE = 50 * 1024 * 1024L // 50MB
    const val CACHE_MAX_AGE = 7 * 24 * 60 * 60L // 7 days
    
    // Network Settings
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}
