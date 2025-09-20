package com.streamingmediaapp.ui.adapters

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.streamingmediaapp.R
import com.streamingmediaapp.data.MediaItem

class MediaItemPresenter : Presenter() {
    
    private val CARD_WIDTH = 313
    private val CARD_HEIGHT = 176
    
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }
        
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        
        return ViewHolder(cardView)
    }
    
    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val mediaItem = item as MediaItem
        val cardView = viewHolder.view as ImageCardView
        
        cardView.titleText = mediaItem.title
        cardView.contentText = mediaItem.description
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
        
        Glide.with(viewHolder.view.context)
            .load(mediaItem.thumbnailUrl)
            .centerCrop()
            .error(R.drawable.default_background)
            .into(cardView.mainImageView)
    }
    
    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.badgeImage = null
        cardView.mainImage = null
    }
    
    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) {
            view.context.getColor(R.color.selected_background)
        } else {
            view.context.getColor(R.color.default_background)
        }
        view.setBackgroundColor(color)
    }
}
