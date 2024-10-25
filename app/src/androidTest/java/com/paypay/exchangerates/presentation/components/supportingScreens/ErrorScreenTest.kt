package com.paypay.exchangerates.presentation.components.supportingScreens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.paypay.exchangerates.presentation.home.HomeViewModel
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test


class ErrorScreenTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorScreen_displaysErrorMessage_andRetryButton() {
        val viewModel = mockk<HomeViewModel>(relaxed = true)
        val errorMessage = "Network error"

        composeTestRule.setContent {
            ErrorScreen(message = errorMessage, viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("An error occurred.\n$errorMessage")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Retry")
            .assertIsDisplayed()
            .performClick()

        verify { viewModel.fetchData() }
    }
}