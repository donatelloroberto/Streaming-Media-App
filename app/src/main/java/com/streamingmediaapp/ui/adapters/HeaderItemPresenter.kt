package com.streamingmediaapp.ui.adapters

import android.view.ViewGroup
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.streamingmediaapp.R

class HeaderItemPresenter : RowHeaderPresenter() {
    
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = super.onCreateViewHolder(parent)
        view.view.setBackgroundColor(parent.context.getColor(R.color.header_background))
        return view
    }
}
