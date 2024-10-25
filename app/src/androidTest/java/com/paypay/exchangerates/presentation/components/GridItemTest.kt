package com.paypay.exchangerates.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.paypay.exchangerates.domain.CurrencyRate
import org.junit.Rule
import org.junit.Test

class GridItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gridItem_displaysCurrencyRateCorrectly() {

        val testCurrencyRate = CurrencyRate(currency = "USD", currencyRate = 1.0)


        composeTestRule.setContent {
            GridItem(currencyRate = testCurrencyRate)
        }


        composeTestRule.onNodeWithText("USD").assertIsDisplayed()


        composeTestRule.onNodeWithText("1.0").assertIsDisplayed()
    }
}