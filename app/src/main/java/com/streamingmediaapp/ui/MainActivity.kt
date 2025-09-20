package com.streamingmediaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.repository.MediaRepository
import com.streamingmediaapp.ui.fragments.MainFragment

class MainActivity : FragmentActivity() {
    
    private lateinit var mediaRepository: MediaRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        mediaRepository = (application as com.streamingmediaapp.StreamingMediaApplication).mediaRepository
        
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }
    
    fun onItemSelected(item: Any?) {
        if (item is MediaItem) {
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.MEDIA_ITEM, item)
            }
            startActivity(intent)
        }
    }
}
