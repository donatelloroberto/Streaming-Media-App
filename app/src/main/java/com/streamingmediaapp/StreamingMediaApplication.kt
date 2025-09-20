package com.streamingmediaapp

import android.app.Application
import androidx.room.Room
import com.streamingmediaapp.database.AppDatabase
import com.streamingmediaapp.repository.MediaRepository
import com.streamingmediaapp.service.MediaPlaybackService

class StreamingMediaApplication : Application() {
    
    lateinit var database: AppDatabase
        private set
    
    lateinit var mediaRepository: MediaRepository
        private set
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "streaming_media_database"
        ).build()
        
        // Initialize repository
        mediaRepository = MediaRepository(database)
    }
}
