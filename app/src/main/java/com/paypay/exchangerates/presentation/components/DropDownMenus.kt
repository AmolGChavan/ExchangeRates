package com.paypay.exchangerates.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize
import com.paypay.exchangerates.domain.CurrencyRate

@Composable
fun DropDownMenus(
    width: Dp,
    modifier: Modifier,
    items: List<CurrencyRate>,
    mSelectedCurrency: CurrencyRate,
    selectedCurr: (CurrencyRate) -> Unit
) {

    var mExpanded by remember { mutableStateOf(false) }

    var mSelectedText by remember { mutableStateOf(mSelectedCurrency.currency) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(modifier = modifier) {

        OutlinedTextField(
            readOnly = true,
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(width)
                .wrapContentHeight()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text("Currency") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.testTag("CurrencyDropdownIcon").clickable { mExpanded = !mExpanded })
            }
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .testTag("CurrencyDropdownMenu")
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            items.forEach { currencyRate ->
                DropdownMenuItem(
                    modifier = Modifier.testTag("CurrencyItem_${currencyRate.currency}"),
                    text = {
                    Text(text = currencyRate.currency)
                }
                    , onClick = {
                    mSelectedText = currencyRate.currency
                    selectedCurr(currencyRate)
                    mExpanded = false
                })
            }
        }
    }
}