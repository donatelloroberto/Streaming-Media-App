package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.ViewModelProvider
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.DetailsActivity
import com.streamingmediaapp.ui.adapters.CardPresenter
import com.streamingmediaapp.viewmodel.FavoritesViewModel

class FavoritesFragment : BrowseSupportFragment() {
    
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var rowsAdapter: ArrayObjectAdapter
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUIElements()
        setupEventListeners()
        setupViewModel()
    }
    
    private fun setupUIElements() {
        title = getString(R.string.favorites)
        headersState = HEADERS_DISABLED
    }
    
    private fun setupEventListeners() {
        onItemViewClickedListener = ItemViewClickedListener()
    }
    
    private fun setupViewModel() {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter
        
        favoritesViewModel.favoriteMedia.observe(viewLifecycleOwner) { favorites ->
            loadFavorites(favorites)
        }
        
        favoritesViewModel.loadFavorites()
    }
    
    private fun loadFavorites(favorites: List<MediaItem>) {
        rowsAdapter.clear()
        
        if (favorites.isNotEmpty()) {
            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            favorites.forEach { item ->
                listRowAdapter.add(item)
            }
            
            val header = HeaderItem("My Favorites")
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
                val intent = android.content.Intent(requireContext(), DetailsActivity::class.java).apply {
                    putExtra(DetailsActivity.MEDIA_ITEM, item)
                }
                startActivity(intent)
            }
        }
    }
}
