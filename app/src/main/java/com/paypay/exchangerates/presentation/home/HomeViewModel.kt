package com.paypay.exchangerates.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paypay.exchangerates.di.CoroutineModule
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.usecase.rates.GetAPIExchangeRates
import com.paypay.exchangerates.domain.usecase.rates.GetCurrencyExchangeRates
import com.paypay.exchangerates.domain.usecase.rates.GetLocalExchangeRates
import com.paypay.exchangerates.domain.usecase.sync.CheckIfLoadCurrencyRatesFromDatabaseUseCase
import com.paypay.exchangerates.presentation.ui_states.UiState
import com.paypay.exchangerates.utils.Constants.API_CALL_FREQUENCY
import com.paypay.exchangerates.utils.GlobleCoroutineExceptionHandler
import com.paypay.exchangerates.utils.round
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getAPIExchangeRates: GetAPIExchangeRates,
    val checkIfLoadCurrencyRatesFromLocalUseCase: CheckIfLoadCurrencyRatesFromDatabaseUseCase,
    val getLocalExchangeRates: GetLocalExchangeRates,
    val getCurrencyExchangeRates: GetCurrencyExchangeRates,
    @CoroutineModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val coroutineExceptionHandler: GlobleCoroutineExceptionHandler,
    private val logger: Logger
) : ViewModel() {


    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            try {
                val rates = getCurrencyExchangeRates()
                _uiState.value = UiState.Success(rates)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(message = "${e.message}")
                logger.e("EXE_RATE_EXCEPTION:${e.stackTrace}")
            }
        }
    }

    private suspend fun getCurrencyExchangeRates() =
        if (checkIfLoadCurrencyRatesFromLocalUseCase(apiCallFrequencyInMinute = API_CALL_FREQUENCY)) {
            getLocalExchangeRates()
        } else {
            getAPIExchangeRates()
        }

    fun fetchData(baseCurrency: CurrencyRate) {
        _uiState.value = UiState.Loading
        viewModelScope.launch(ioDispatcher + coroutineExceptionHandler) {
            try {
                _uiState.value = UiState.Success(
                    rates = getCurrencyExchangeRates(
                        baseCurrency,
                        checkIfLoadCurrencyRatesFromLocalUseCase(apiCallFrequencyInMinute = API_CALL_FREQUENCY)
                    )
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(message = "${e.message}")
                logger.e("EXE_RATE_EXCEPTION:${e.stackTrace}")
            }
        }
    }
}

