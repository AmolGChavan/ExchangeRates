package com.paypay.exchangerates.presentation.home


import HomeDataContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.paypay.exchangerates.presentation.components.supportingScreens.CircularLoaderScreen
import com.paypay.exchangerates.presentation.components.supportingScreens.ErrorScreen
import com.paypay.exchangerates.presentation.ui_states.UiState

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        UiState.Loading -> {
            CircularLoaderScreen()
        }
        is UiState.Success -> {
            HomeDataContent((uiState as UiState.Success).rates, viewModel)
        }
        is UiState.Error -> {
            ErrorScreen((uiState as UiState.Error).message, viewModel)
        }
    }
}








