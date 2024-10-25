package com.paypay.exchangerates.presentation.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.paypay.exchangerates.domain.CurrencyRate
import org.junit.Rule
import org.junit.Test

class GridViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gridView_displaysCorrectNumberOfItems() {

        val items = listOf(
            CurrencyRate(currency = "USD", currencyRate = 1.0),
            CurrencyRate(currency = "EUR", currencyRate = 0.85),
            CurrencyRate(currency = "JPY", currencyRate = 110.0),
            CurrencyRate(currency = "GBP", currencyRate = 0.75)
        )


        composeTestRule.setContent {
            GridView(modifier = Modifier.testTag("GridView"), items = items)
        }


        composeTestRule.onAllNodesWithTag("GridItem").assertCountEquals(4)


        composeTestRule.onNodeWithText("USD").assertIsDisplayed()
        composeTestRule.onNodeWithText("EUR").assertIsDisplayed()
        composeTestRule.onNodeWithText("JPY").assertIsDisplayed()
        composeTestRule.onNodeWithText("GBP").assertIsDisplayed()
    }
}