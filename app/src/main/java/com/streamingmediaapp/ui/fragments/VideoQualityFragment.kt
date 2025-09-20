package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.R

class VideoQualityFragment : GuidedStepSupportFragment() {
    
    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(1L)
                .title("Auto")
                .description("Automatically adjust quality based on connection")
                .checkSetId(1)
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(2L)
                .title("1080p")
                .description("High Definition (1080p)")
                .checkSetId(1)
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(3L)
                .title("720p")
                .description("High Definition (720p)")
                .checkSetId(1)
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(4L)
                .title("480p")
                .description("Standard Definition (480p)")
                .checkSetId(1)
                .build()
        )
    }
    
    override fun onGuidedActionClicked(action: GuidedAction) {
        // Save video quality preference
        // Navigate back
        parentFragmentManager.popBackStack()
    }
}
