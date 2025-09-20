package com.streamingmediaapp.ui.adapters

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.streamingmediaapp.data.MediaItem

class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    
    override fun onBindDescription(
        viewHolder: AbstractDetailsDescriptionPresenter.ViewHolder,
        item: Any
    ) {
        if (item is MediaItem) {
            viewHolder.title.text = item.title
            viewHolder.subtitle.text = item.description
            viewHolder.body.text = buildString {
                append("Duration: ${item.duration}\n")
                append("Category: ${item.category}\n")
                if (item.rating > 0) {
                    append("Rating: ${item.rating}/5.0")
                }
            }
        }
    }
}
