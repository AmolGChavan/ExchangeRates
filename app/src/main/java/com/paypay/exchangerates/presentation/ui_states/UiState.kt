package com.paypay.exchangerates.presentation.ui_states

import com.paypay.exchangerates.domain.CurrencyRate

sealed class UiState {
    object Loading : UiState()
    data class Success(val rates: List<CurrencyRate>) : UiState()
    data class Error(val message: String) : UiState()
}