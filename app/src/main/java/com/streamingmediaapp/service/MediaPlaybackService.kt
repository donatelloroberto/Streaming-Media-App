package com.streamingmediaapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem as ExoMediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.PlayerActivity

class MediaPlaybackService : MediaSessionService() {
    
    private var mediaSession: MediaSession? = null
    private var exoPlayer: ExoPlayer? = null
    
    companion object {
        const val CHANNEL_ID = "media_playback_channel"
        const val NOTIFICATION_ID = 1
        const val MEDIA_ITEM = "media_item"
    }
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initializePlayer()
        initializeMediaSession()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Media Playback",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Media playback notifications"
            }
            
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
    }
    
    private fun initializeMediaSession() {
        mediaSession = MediaSession.Builder(this, exoPlayer!!).build()
    }
    
    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mediaItem = intent?.getParcelableExtra<MediaItem>(MEDIA_ITEM)
        
        if (mediaItem != null) {
            startForeground(NOTIFICATION_ID, createNotification(mediaItem))
            playMedia(mediaItem)
        }
        
        return START_STICKY
    }
    
    private fun playMedia(mediaItem: MediaItem) {
        val exoMediaItem = ExoMediaItem.fromUri(mediaItem.videoUrl)
        exoPlayer?.setMediaItem(exoMediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }
    
    private fun createNotification(mediaItem: MediaItem): Notification {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.MEDIA_ITEM, mediaItem)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(mediaItem.title)
            .setContentText("Now Playing")
            .setSmallIcon(R.drawable.ic_play_arrow)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        mediaSession?.run {
            player.release()
            release()
        }
    }
}
