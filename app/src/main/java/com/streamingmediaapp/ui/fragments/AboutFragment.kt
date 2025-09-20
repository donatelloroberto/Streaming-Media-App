package com.streamingmediaapp.ui.fragments

import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.*
import com.streamingmediaapp.BuildConfig
import com.streamingmediaapp.R

class AboutFragment : GuidedStepSupportFragment() {
    
    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(1L)
                .title("App Version")
                .description(BuildConfig.VERSION_NAME)
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(2L)
                .title("Build Number")
                .description(BuildConfig.VERSION_CODE.toString())
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(3L)
                .title("Privacy Policy")
                .description("View our privacy policy")
                .build()
        )
        
        actions.add(
            GuidedAction.Builder(requireContext())
                .id(4L)
                .title("Terms of Service")
                .description("View terms of service")
                .build()
        )
    }
    
    override fun onGuidedActionClicked(action: GuidedAction) {
        when (action.id) {
            3L -> {
                // Open privacy policy
            }
            4L -> {
                // Open terms of service
            }
        }
    }
}
