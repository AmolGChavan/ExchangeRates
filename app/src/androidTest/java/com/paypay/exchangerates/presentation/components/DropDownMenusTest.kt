package com.paypay.exchangerates.presentation.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.paypay.exchangerates.domain.CurrencyRate
import org.junit.Rule
import org.junit.Test

class DropDownMenusTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dropdownMenu_displaysCorrectItems() {

        val items = listOf(
            CurrencyRate(currency = "USD", currencyRate = 1.0),
            CurrencyRate(currency = "EUR", currencyRate = 0.85),
            CurrencyRate(currency = "JPY", currencyRate = 110.0)
        )
        val selectedCurrency = CurrencyRate(currency = "USD", currencyRate = 1.0)


        composeTestRule.setContent {
            DropDownMenus(
                width = 200.dp,
                modifier = Modifier.testTag("DropDownMenus"),
                items = items,
                mSelectedCurrency = selectedCurrency,
                selectedCurr = {}
            )
        }

        // Open the dropdown
        composeTestRule.onNodeWithTag("CurrencyDropdownIcon").performClick()


        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag("CurrencyItem_USD").fetchSemanticsNodes()
                .isNotEmpty() ||
                    composeTestRule.onAllNodesWithTag("CurrencyItem_EUR").fetchSemanticsNodes()
                        .isNotEmpty() ||
                    composeTestRule.onAllNodesWithTag("CurrencyItem_JPY").fetchSemanticsNodes()
                        .isNotEmpty()
        }


        items.forEach { currencyRate ->
            composeTestRule.onNodeWithTag("CurrencyItem_${currencyRate.currency}")
                .assertIsDisplayed()
        }
    }
}