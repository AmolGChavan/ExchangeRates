package com.paypay.exchangerates.presentation.components.supportingScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paypay.exchangerates.presentation.home.HomeViewModel
import com.paypay.exchangerates.presentation.ui_states.UiState

@Composable
internal fun ErrorScreen(
    message: String,
    viewModel: HomeViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ErrorScreen"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("An error occurred.\n$message")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { viewModel.fetchData() }) {
            Text("Retry")
        }
    }
}





