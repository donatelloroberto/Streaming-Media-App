package com.streamingmediaapp.ui.adapters

import android.view.ViewGroup
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.DetailsOverviewRowPresenter
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import androidx.leanback.widget.Presenter

class CustomFullWidthDetailsOverviewRowPresenter(
    detailsPresenter: Presenter
) : FullWidthDetailsOverviewRowPresenter(detailsPresenter) {
    
    override fun onBindRowViewHolder(
        viewHolder: androidx.leanback.widget.RowPresenter.ViewHolder,
        item: Any
    ) {
        super.onBindRowViewHolder(viewHolder, item)
        
        val detailsViewHolder = viewHolder as DetailsOverviewRowPresenter.ViewHolder
        val detailsOverviewRow = item as DetailsOverviewRow
        
        // Customize the details view
        detailsViewHolder.imageView?.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
    }
    
    override fun onUnbindRowViewHolder(viewHolder: androidx.leanback.widget.RowPresenter.ViewHolder) {
        super.onUnbindRowViewHolder(viewHolder)
    }
}
