package com.streamingmediaapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.streamingmediaapp.R

object ImageLoader {
    
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_background)
                    .error(R.drawable.default_background)
                    .centerCrop()
            )
            .into(imageView)
    }
    
    fun loadThumbnail(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_background)
                    .error(R.drawable.default_background)
                    .centerCrop()
                    .override(300, 200)
            )
            .into(imageView)
    }
    
    fun loadBanner(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.app_banner)
                    .error(R.drawable.app_banner)
                    .centerCrop()
                    .override(800, 450)
            )
            .into(imageView)
    }
    
    fun preloadImages(context: Context, urls: List<String>) {
        urls.forEach { url ->
            Glide.with(context)
                .load(url)
                .preload()
        }
    }
}
