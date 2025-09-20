package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.ViewModelProvider
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem
import com.streamingmediaapp.ui.DetailsActivity
import com.streamingmediaapp.ui.adapters.CardPresenter
import com.streamingmediaapp.viewmodel.SearchViewModel

class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {
    
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var rowsAdapter: ArrayObjectAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUIElements()
        setupViewModel()
    }
    
    private fun setupUIElements() {
        setSearchResultProvider(this)
        setOnItemViewClickedListener(ItemViewClickedListener())
    }
    
    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        
        searchViewModel.searchResults.observe(this) { results ->
            loadSearchResults(results)
        }
    }
    
    override fun getResultsAdapter(): ObjectAdapter {
        return rowsAdapter
    }
    
    override fun onQueryTextChange(newQuery: String): Boolean {
        if (newQuery.isNotEmpty()) {
            searchViewModel.searchMedia(newQuery)
        } else {
            rowsAdapter.clear()
        }
        return true
    }
    
    override fun onQueryTextSubmit(query: String): Boolean {
        searchViewModel.searchMedia(query)
        return true
    }
    
    private fun loadSearchResults(results: List<MediaItem>) {
        rowsAdapter.clear()
        
        if (results.isNotEmpty()) {
            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            results.forEach { item ->
                listRowAdapter.add(item)
            }
            
            val header = HeaderItem("Search Results")
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
