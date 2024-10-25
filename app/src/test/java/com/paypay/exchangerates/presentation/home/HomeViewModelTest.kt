package com.paypay.exchangerates.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.usecase.rates.GetAPIExchangeRates
import com.paypay.exchangerates.domain.usecase.rates.GetCurrencyExchangeRates
import com.paypay.exchangerates.domain.usecase.rates.GetLocalExchangeRates
import com.paypay.exchangerates.domain.usecase.sync.CheckIfLoadCurrencyRatesFromDatabaseUseCase
import com.paypay.exchangerates.presentation.ui_states.UiState
import com.paypay.exchangerates.utils.GlobleCoroutineExceptionHandler
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val getAPIExchangeRates: GetAPIExchangeRates = mockk()
    private val checkIfLoadCurrencyRatesFromLocalUseCase: CheckIfLoadCurrencyRatesFromDatabaseUseCase = mockk()
    private val getLocalExchangeRates: GetLocalExchangeRates = mockk()
    private val getCurrencyExchangeRates: GetCurrencyExchangeRates = mockk()
    private val logger: Logger = mockk(relaxed = true)
    private val coroutineExceptionHandler = GlobleCoroutineExceptionHandler(mockk<Logger>())
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(
            getAPIExchangeRates,
            checkIfLoadCurrencyRatesFromLocalUseCase,
            getLocalExchangeRates,
            getCurrencyExchangeRates,
            testDispatcher,
            coroutineExceptionHandler,
            logger
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchData should emit Success state when local rates are loaded`() = runTest {
        val currencyRates = listOf(CurrencyRate("USD", 1.0))
        coEvery { checkIfLoadCurrencyRatesFromLocalUseCase(any()) } returns true
        coEvery { getLocalExchangeRates() } returns currencyRates

        viewModel.fetchData()

        advanceTimeBy(1000)

        assert(viewModel.uiState.value is UiState.Success)
        assert((viewModel.uiState.value as UiState.Success).rates == currencyRates)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchData should emit Success state when API rates are loaded`() = runTest {
        val currencyRates = listOf(CurrencyRate("EUR", 0.85))
        coEvery { checkIfLoadCurrencyRatesFromLocalUseCase(any()) } returns false
        coEvery { getAPIExchangeRates() } returns currencyRates

        viewModel.fetchData()

        advanceTimeBy(1000)

        assert(viewModel.uiState.value is UiState.Success)
        assert((viewModel.uiState.value as UiState.Success).rates == currencyRates)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchData should emit Error state when an exception is thrown`() = runTest {
        val exception = RuntimeException("Network Error")
        coEvery { checkIfLoadCurrencyRatesFromLocalUseCase(any()) } returns false
        coEvery { getAPIExchangeRates() } throws exception

        viewModel.fetchData()

        advanceTimeBy(1000)

        assert(viewModel.uiState.value is UiState.Error)
        assert((viewModel.uiState.value as UiState.Error).message == "Network Error")
        verify { logger.e(any()) }
    }
}