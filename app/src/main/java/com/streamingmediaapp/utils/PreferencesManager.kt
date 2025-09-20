package com.streamingmediaapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "streaming_media_prefs"
        private const val KEY_VIDEO_QUALITY = "video_quality"
        private const val KEY_AUDIO_LANGUAGE = "audio_language"
        private const val KEY_SUBTITLES_ENABLED = "subtitles_enabled"
        private const val KEY_PARENTAL_CONTROLS_ENABLED = "parental_controls_enabled"
        private const val KEY_MAX_CONTENT_RATING = "max_content_rating"
        private const val KEY_AUTO_PLAY = "auto_play"
        private const val KEY_LAST_PLAYED_POSITION = "last_played_position_"
        private const val KEY_USER_PREFERENCES_LOADED = "user_preferences_loaded"
        
        // Default values
        const val DEFAULT_VIDEO_QUALITY = "auto"
        const val DEFAULT_AUDIO_LANGUAGE = "en"
        const val DEFAULT_MAX_CONTENT_RATING = "PG-13"
    }
    
    var videoQuality: String
        get() = prefs.getString(KEY_VIDEO_QUALITY, DEFAULT_VIDEO_QUALITY) ?: DEFAULT_VIDEO_QUALITY
        set(value) = prefs.edit().putString(KEY_VIDEO_QUALITY, value).apply()
    
    var audioLanguage: String
        get() = prefs.getString(KEY_AUDIO_LANGUAGE, DEFAULT_AUDIO_LANGUAGE) ?: DEFAULT_AUDIO_LANGUAGE
        set(value) = prefs.edit().putString(KEY_AUDIO_LANGUAGE, value).apply()
    
    var subtitlesEnabled: Boolean
        get() = prefs.getBoolean(KEY_SUBTITLES_ENABLED, false)
        set(value) = prefs.edit().putBoolean(KEY_SUBTITLES_ENABLED, value).apply()
    
    var parentalControlsEnabled: Boolean
        get() = prefs.getBoolean(KEY_PARENTAL_CONTROLS_ENABLED, false)
        set(value) = prefs.edit().putBoolean(KEY_PARENTAL_CONTROLS_ENABLED, value).apply()
    
    var maxContentRating: String
        get() = prefs.getString(KEY_MAX_CONTENT_RATING, DEFAULT_MAX_CONTENT_RATING) ?: DEFAULT_MAX_CONTENT_RATING
        set(value) = prefs.edit().putString(KEY_MAX_CONTENT_RATING, value).apply()
    
    var autoPlay: Boolean
        get() = prefs.getBoolean(KEY_AUTO_PLAY, true)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_PLAY, value).apply()
    
    fun getLastPlayedPosition(mediaId: String): Long {
        return prefs.getLong(KEY_LAST_PLAYED_POSITION + mediaId, 0L)
    }
    
    fun setLastPlayedPosition(mediaId: String, position: Long) {
        prefs.edit().putLong(KEY_LAST_PLAYED_POSITION + mediaId, position).apply()
    }
    
    var userPreferencesLoaded: Boolean
        get() = prefs.getBoolean(KEY_USER_PREFERENCES_LOADED, false)
        set(value) = prefs.edit().putBoolean(KEY_USER_PREFERENCES_LOADED, value).apply()
    
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}
