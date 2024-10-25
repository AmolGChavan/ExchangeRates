package com.paypay.exchangerates.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class NumericTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun numericTextField_callsOnDoneChange() = runBlocking {
        // Arrange
        var inputValue by mutableStateOf("")
        val onDoneChangeCalled = mutableStateOf(false)

        composeTestRule.setContent {
            NumericTextField(
                modifier = Modifier.testTag("NumericTextField"),
                value = inputValue,
                label = "Enter Amount",
                onValueChange = {
                    inputValue = it
                    println("onValueChange Input value: $it ")
                },
                onDoneChange = { onDoneChangeCalled.value = true }
            )
        }

        // Act
        composeTestRule.onNodeWithTag("NumericTextField")
            .performTextInput("20.0")
        composeTestRule.onNodeWithTag("NumericTextField")
            .performImeAction()
        // Assert
        println("Input value: $inputValue ")
        assert(onDoneChangeCalled.value) { "onDoneChange was not called" }
        assert(inputValue == "20.0") { "The input value did not update correctly" }
    }

    @Test
    fun numericTextField_only_accepts_numeric_input() {
        // Arrange
        var inputValue by mutableStateOf("")

        composeTestRule.setContent {
            NumericTextField(
                modifier = Modifier.testTag("NumericTextField"),
                value = inputValue,
                label = "Enter Amount",
                onValueChange = { inputValue = it },
                onDoneChange = {}
            )
        }

        // Act
        composeTestRule.onNodeWithTag("NumericTextField").performTextInput("abc")
        composeTestRule.onNodeWithTag("NumericTextField").performTextInput("123.45") // Valid input

        // Assert
        assert(inputValue == "123.45") { "The input value did not update correctly to a valid number" }
    }
}