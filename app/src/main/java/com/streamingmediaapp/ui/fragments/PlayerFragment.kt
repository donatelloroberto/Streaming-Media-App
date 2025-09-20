package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem as ExoMediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem

class PlayerFragment : Fragment() {
    
    private lateinit var mediaItem: MediaItem
    private lateinit var playerView: PlayerView
    private var exoPlayer: ExoPlayer? = null
    
    companion object {
        private const val MEDIA_ITEM = "media_item"
        
        fun newInstance(mediaItem: MediaItem): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putParcelable(MEDIA_ITEM, mediaItem)
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaItem = arguments?.getParcelable(MEDIA_ITEM) ?: return
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        playerView = view.findViewById(R.id.player_view)
        initializePlayer()
    }
    
    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        playerView.player = exoPlayer
        
        val exoMediaItem = ExoMediaItem.fromUri(mediaItem.videoUrl)
        exoPlayer?.setMediaItem(exoMediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        exoPlayer?.release()
        exoPlayer = null
    }
    
    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }
    
    override fun onResume() {
        super.onResume()
        exoPlayer?.play()
    }
}
