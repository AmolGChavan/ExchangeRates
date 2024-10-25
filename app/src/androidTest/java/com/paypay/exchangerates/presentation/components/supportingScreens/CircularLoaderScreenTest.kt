package com.paypay.exchangerates.presentation.components.supportingScreens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test


class CircularLoaderScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circularLoaderScreen_displaysLoader() {
        composeTestRule.setContent {
            CircularLoaderScreen()
        }

        // Check if the CircularProgressIndicator is displayed
        composeTestRule.onNodeWithContentDescription("Loading")
            .assertIsDisplayed()

        // Check if the CircularLoader Box is displayed
        composeTestRule.onNodeWithTag("CircularLoader")
            .assertIsDisplayed()
    }
}