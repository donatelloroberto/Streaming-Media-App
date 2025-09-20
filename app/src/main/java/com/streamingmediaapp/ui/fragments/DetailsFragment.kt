package com.streamingmediaapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.DetailsActivity
import com.streamingmediaapp.ui.PlayerActivity

class DetailsFragment : DetailsSupportFragment() {
    
    private lateinit var mediaItem: MediaItem
    private lateinit var detailsOverviewRow: DetailsOverviewRow
    private lateinit var presenterSelector: ClassPresenterSelector
    
    companion object {
        private const val MEDIA_ITEM = "media_item"
        
        fun newInstance(mediaItem: MediaItem): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(MEDIA_ITEM, mediaItem)
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        mediaItem = arguments?.getParcelable(MEDIA_ITEM) ?: return
        
        setupUIElements()
        setupDetailsOverviewRow()
        setupEventListeners()
    }
    
    private fun setupUIElements() {
        title = mediaItem.title
    }
    
    private fun setupDetailsOverviewRow() {
        detailsOverviewRow = DetailsOverviewRow(mediaItem)
        detailsOverviewRow.imageDrawable = resources.getDrawable(R.drawable.default_background, null)
        
        val actionsAdapter = ArrayObjectAdapter()
        actionsAdapter.add(Action(1, "Play", "Play this media"))
        actionsAdapter.add(Action(2, "Add to Favorites", "Add to favorites"))
        detailsOverviewRow.actionsAdapter = actionsAdapter
        
        presenterSelector = ClassPresenterSelector()
        presenterSelector.addClassPresenter(
            DetailsOverviewRow::class.java,
            FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter())
        )
        
        val adapter = ArrayObjectAdapter(presenterSelector)
        adapter.add(detailsOverviewRow)
        this.adapter = adapter
    }
    
    private fun setupEventListeners() {
        onActionClickedListener = OnActionClickedListener { action ->
            when (action.id) {
                1L -> {
                    // Play action
                    val intent = Intent(requireContext(), PlayerActivity::class.java).apply {
                        putExtra(PlayerActivity.MEDIA_ITEM, mediaItem)
                    }
                    startActivity(intent)
                }
                2L -> {
                    // Add to favorites action
                    (activity as? DetailsActivity)?.let { detailsActivity ->
                        // Handle add to favorites
                    }
                }
            }
        }
    }
    
    private inner class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
        override fun onBindDescription(
            viewHolder: AbstractDetailsDescriptionPresenter.ViewHolder,
            item: Any
        ) {
            if (item is MediaItem) {
                viewHolder.title.text = item.title
                viewHolder.subtitle.text = item.description
                viewHolder.body.text = "Duration: ${item.duration}\nCategory: ${item.category}"
            }
        }
    }
}
