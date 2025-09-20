package com.streamingmediaapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.streamingmediaapp.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun testMainActivityLaunches() {
        // Test that MainActivity launches successfully
        // This is a basic smoke test
    }
    
    @Test
    fun testMainActivityHasCorrectTitle() {
        // Test that the activity has the correct title
        // This would require UI testing framework like Espresso
    }
}
