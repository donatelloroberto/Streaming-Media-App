package com.streamingmediaapp.network

import com.streamingmediaapp.data.MediaItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    
    @GET("media/featured")
    suspend fun getFeaturedMedia(): Response<List<MediaItem>>
    
    @GET("media/categories")
    suspend fun getCategories(): Response<List<String>>
    
    @GET("media/category/{category}")
    suspend fun getMediaByCategory(@Path("category") category: String): Response<List<MediaItem>>
    
    @GET("media/search")
    suspend fun searchMedia(@Query("query") query: String): Response<List<MediaItem>>
    
    @GET("media/{id}")
    suspend fun getMediaById(@Path("id") id: String): Response<MediaItem>
    
    @GET("media/trending")
    suspend fun getTrendingMedia(): Response<List<MediaItem>>
    
    @GET("media/recommended")
    suspend fun getRecommendedMedia(): Response<List<MediaItem>>
}
