package com.paypay.exchangerates.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.presentation.Dimens

@Composable
fun GridView(modifier: Modifier, items: List<CurrencyRate>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        contentPadding = PaddingValues(Dimens.Dp8)
    ) {
        items(items) { currencyRates ->
            GridItem(currencyRates)
        }
    }
}


@Composable
fun GridItem(currencyRate: CurrencyRate) {
    Card(
        modifier = Modifier.testTag("GridItem")
            .padding(Dimens.Dp8)
            .fillMaxSize()

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Dp10),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currencyRate.currency)
            Text(
                text = "${currencyRate.currencyRate}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }


    }
}
