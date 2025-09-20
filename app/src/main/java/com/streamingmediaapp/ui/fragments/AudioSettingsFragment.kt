package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R

class AudioSettingsFragment : GuidedStepSupportFragment() {
    
    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(1L)
                .title("Audio Language")
                .description("Select preferred audio language")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(2L)
                .title("Subtitles")
                .description("Configure subtitle preferences")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(3L)
                .title("Audio Output")
                .description("Select audio output device")
                .build()
        )
    }
    
    override fun onGuidedActionClicked(action: GuidedAction) {
        when (action.id) {
            1L -> {
                // Navigate to audio language settings
            }
            2L -> {
                // Navigate to subtitle settings
            }
            3L -> {
                // Navigate to audio output settings
            }
        }
    }
}
