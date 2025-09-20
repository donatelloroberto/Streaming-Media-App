package com.streamingmediaapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.ViewModelProvider
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.DetailsActivity
import com.streamingmediaapp.ui.PlayerActivity
import com.streamingmediaapp.ui.adapters.CardPresenter
import com.streamingmediaapp.ui.adapters.HeaderItemPresenter
import com.streamingmediaapp.viewmodel.MainViewModel

class MainFragment : BrowseSupportFragment() {
    
    private lateinit var mainViewModel: MainViewModel
    private lateinit var rowsAdapter: ArrayObjectAdapter
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUIElements()
        setupEventListeners()
        setupViewModel()
    }
    
    private fun setupUIElements() {
        title = getString(R.string.app_name)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        
        // Set colors
        brandColor = resources.getColor(R.color.primary_color, null)
        searchAffordanceColor = resources.getColor(R.color.accent_color, null)
    }
    
    private fun setupEventListeners() {
        onItemViewClickedListener = ItemViewClickedListener()
        onItemViewSelectedListener = ItemViewSelectedListener()
    }
    
    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter
        
        mainViewModel.mediaCategories.observe(viewLifecycleOwner) { categories ->
            loadRows(categories)
        }
        
        mainViewModel.loadMediaContent()
    }
    
    private fun loadRows(categories: List<Pair<String, List<MediaItem>>>) {
        rowsAdapter.clear()
        
        categories.forEach { (category, items) ->
            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            items.forEach { item ->
                listRowAdapter.add(item)
            }
            
            val header = HeaderItem(category)
            val listRow = ListRow(header, listRowAdapter)
            rowsAdapter.add(listRow)
        }
    }
    
    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is MediaItem) {
                val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
                    putExtra(DetailsActivity.MEDIA_ITEM, item)
                }
                startActivity(intent)
            }
        }
    }
    
    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            // Handle item selection for preview
        }
    }
}
