package com.streamingmediaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.fragments.DetailsFragment

class DetailsActivity : FragmentActivity() {
    
    companion object {
        const val MEDIA_ITEM = "media_item"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        
        val mediaItem = intent.getParcelableExtra<MediaItem>(MEDIA_ITEM)
        
        if (savedInstanceState == null && mediaItem != null) {
            val fragment = DetailsFragment.newInstance(mediaItem)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_fragment, fragment)
                .commitNow()
        }
    }
    
    fun playMedia(mediaItem: MediaItem) {
        val intent = Intent(this, PlayerActivity::class.java).apply {
            putExtra(PlayerActivity.MEDIA_ITEM, mediaItem)
        }
        startActivity(intent)
    }
}
