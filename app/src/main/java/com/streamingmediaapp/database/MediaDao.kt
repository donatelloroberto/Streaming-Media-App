package com.streamingmediaapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {
    
    @Query("SELECT * FROM media_items WHERE isFavorite = 1")
    suspend fun getFavoriteMedia(): List<MediaItemEntity>
    
    @Query("SELECT * FROM media_items WHERE isFavorite = 1")
    fun getFavoriteMediaFlow(): Flow<List<MediaItemEntity>>
    
    @Query("SELECT * FROM media_items WHERE category = :category")
    suspend fun getMediaByCategory(category: String): List<MediaItemEntity>
    
    @Query("SELECT * FROM media_items WHERE id = :id")
    suspend fun getMediaById(id: String): MediaItemEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(mediaItem: MediaItemEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItems(mediaItems: List<MediaItemEntity>)
    
    @Update
    suspend fun updateMediaItem(mediaItem: MediaItemEntity)
    
    @Delete
    suspend fun deleteMediaItem(mediaItem: MediaItemEntity)
    
    @Query("DELETE FROM media_items WHERE id = :id")
    suspend fun deleteMediaById(id: String)
    
    @Query("DELETE FROM media_items")
    suspend fun deleteAllMedia()
}
