package com.streamingmediaapp.utils

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PreferencesManagerTest {
    
    @Mock
    private lateinit var context: Context
    
    @Mock
    private lateinit var sharedPreferences: SharedPreferences
    
    @Mock
    private lateinit var editor: SharedPreferences.Editor
    
    private lateinit var preferencesManager: PreferencesManager
    
    @Before
    fun setUp() {
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)
        `when`(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor)
        `when`(editor.putLong(anyString(), anyLong())).thenReturn(editor)
        `when`(editor.apply()).then { }
        
        preferencesManager = PreferencesManager(context)
    }
    
    @Test
    fun testVideoQualityDefault() {
        `when`(sharedPreferences.getString(PreferencesManager.KEY_VIDEO_QUALITY, PreferencesManager.DEFAULT_VIDEO_QUALITY))
            .thenReturn(PreferencesManager.DEFAULT_VIDEO_QUALITY)
        
        assertEquals(PreferencesManager.DEFAULT_VIDEO_QUALITY, preferencesManager.videoQuality)
    }
    
    @Test
    fun testVideoQualitySet() {
        val quality = "1080p"
        preferencesManager.videoQuality = quality
        
        verify(editor).putString(PreferencesManager.KEY_VIDEO_QUALITY, quality)
        verify(editor).apply()
    }
    
    @Test
    fun testAudioLanguageDefault() {
        `when`(sharedPreferences.getString(PreferencesManager.KEY_AUDIO_LANGUAGE, PreferencesManager.DEFAULT_AUDIO_LANGUAGE))
            .thenReturn(PreferencesManager.DEFAULT_AUDIO_LANGUAGE)
        
        assertEquals(PreferencesManager.DEFAULT_AUDIO_LANGUAGE, preferencesManager.audioLanguage)
    }
    
    @Test
    fun testSubtitlesEnabledDefault() {
        `when`(sharedPreferences.getBoolean(PreferencesManager.KEY_SUBTITLES_ENABLED, false))
            .thenReturn(false)
        
        assertFalse(preferencesManager.subtitlesEnabled)
    }
    
    @Test
    fun testSubtitlesEnabledSet() {
        preferencesManager.subtitlesEnabled = true
        
        verify(editor).putBoolean(PreferencesManager.KEY_SUBTITLES_ENABLED, true)
        verify(editor).apply()
    }
}
