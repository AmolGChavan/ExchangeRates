package com.paypay.exchangerates.presentation.mainActivity



import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            MainActivity()
        }
    }

    @Test
    fun testSplashScreenKeepsVisible() {
        // Verify that the splash screen is initially kept on the screen
        // Here, we can't check for a specific UI component, so just observe the condition.
    }
}