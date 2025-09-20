package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R

class ParentalControlsFragment : GuidedStepSupportFragment() {
    
    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(1L)
                .title("Enable Parental Controls")
                .description("Restrict content based on ratings")
                .checkSetId(1)
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(2L)
                .title("Content Rating")
                .description("Set maximum allowed content rating")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(3L)
                .title("PIN Protection")
                .description("Require PIN for restricted content")
                .build()
        )
    }
    
    override fun onGuidedActionClicked(action: GuidedAction) {
        when (action.id) {
            1L -> {
                // Toggle parental controls
            }
            2L -> {
                // Navigate to content rating settings
            }
            3L -> {
                // Navigate to PIN settings
            }
        }
    }
}
