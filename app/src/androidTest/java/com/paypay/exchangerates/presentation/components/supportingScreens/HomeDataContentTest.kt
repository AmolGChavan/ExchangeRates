package com.paypay.exchangerates.presentation.components.supportingScreens

import HomeDataContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.presentation.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeDataContentTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        // Mock the ViewModel
        viewModel = mockk(relaxed = true)
        every { viewModel.fetchData(any()) } returns Unit
    }

    @Test
    fun homeDataContent_showsOnlyNumericTextFieldAfterInput() {
        // Arrange: Prepare the data for the test
        val items = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85)
        )

        // Act: Set the content with HomeDataContent
        composeTestRule.setContent {
            HomeDataContent(items = items, viewModel = viewModel)
        }
        composeTestRule.onNodeWithTag("NumericTextField").performTextInput("2.0")
        composeTestRule.onNodeWithTag("NumericTextField").performImeAction()
        composeTestRule.onNodeWithTag("NumericTextField").assertIsDisplayed()
        composeTestRule.onNodeWithTag("DropDownMenus").assertIsDisplayed()
        composeTestRule.onNodeWithTag("GridView").assertIsDisplayed()
    }
}