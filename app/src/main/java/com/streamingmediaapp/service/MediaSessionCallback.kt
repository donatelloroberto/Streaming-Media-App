package com.streamingmediaapp.service

import androidx.media3.common.MediaItem as ExoMediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.streamingmediaapp.data.MediaItem

class MediaSessionCallback : MediaSession.Callback {
    
    override fun onPlay(
        session: MediaSession,
        controller: MediaSession.ControllerInfo
    ): androidx.media3.common.PlayerResult {
        session.player.play()
        return androidx.media3.common.PlayerResult.accept()
    }
    
    override fun onPause(
        session: MediaSession,
        controller: MediaSession.ControllerInfo
    ): androidx.media3.common.PlayerResult {
        session.player.pause()
        return androidx.media3.common.PlayerResult.accept()
    }
    
    override fun onStop(
        session: MediaSession,
        controller: MediaSession.ControllerInfo
    ): androidx.media3.common.PlayerResult {
        session.player.stop()
        return androidx.media3.common.PlayerResult.accept()
    }
    
    override fun onSeekTo(
        session: MediaSession,
        controller: MediaSession.ControllerInfo,
        positionMs: Long
    ): androidx.media3.common.PlayerResult {
        session.player.seekTo(positionMs)
        return androidx.media3.common.PlayerResult.accept()
    }
    
    override fun onSetMediaItem(
        session: MediaSession,
        controller: MediaSession.ControllerInfo,
        mediaItem: ExoMediaItem,
        startPositionMs: Long,
        startPlaying: Boolean
    ): androidx.media3.common.PlayerResult {
        session.player.setMediaItem(mediaItem, startPositionMs)
        if (startPlaying) {
            session.player.play()
        }
        return androidx.media3.common.PlayerResult.accept()
    }
}
