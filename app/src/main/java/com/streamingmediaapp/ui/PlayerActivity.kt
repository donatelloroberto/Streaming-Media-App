package com.streamingmediaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.fragments.PlayerFragment

class PlayerActivity : FragmentActivity() {
    
    companion object {
        const val MEDIA_ITEM = "media_item"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        
        val mediaItem = intent.getParcelableExtra<MediaItem>(MEDIA_ITEM)
        
        if (savedInstanceState == null && mediaItem != null) {
            val fragment = PlayerFragment.newInstance(mediaItem)
            supportFragmentManager.beginTransaction()
                .replace(R.id.player_fragment, fragment)
                .commitNow()
        }
    }
}
