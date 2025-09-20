package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R

class SettingsFragment : GuidedStepSupportFragment() {
    
    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(1L)
                .title("Video Quality")
                .description("Adjust video playback quality")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(2L)
                .title("Audio Settings")
                .description("Configure audio preferences")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(3L)
                .title("Parental Controls")
                .description("Set up content restrictions")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(4L)
                .title("About")
                .description("App information and version")
                .build()
        )
    }
    
    override fun onGuidedActionClicked(action: GuidedAction) {
        when (action.id) {
            1L -> {
                // Navigate to video quality settings
                add(fragmentManager, VideoQualityFragment())
            }
            2L -> {
                // Navigate to audio settings
                add(fragmentManager, AudioSettingsFragment())
            }
            3L -> {
                // Navigate to parental controls
                add(fragmentManager, ParentalControlsFragment())
            }
            4L -> {
                // Navigate to about
                add(fragmentManager, AboutFragment())
            }
        }
    }
}
