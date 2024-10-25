package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.data.remote.ExchangeRatesApi
import com.paypay.exchangerates.data.remote.dto.ExchangeRatesResponse
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class GetExchangeRatesRepositoryImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var exchangeRatesApi: ExchangeRatesApi

    @Inject
    lateinit var localExchangeRatesRepository: LocalExchangeRatesRepository

    @Inject
    lateinit var apiSyncTimeRepository: APISyncTimeRepository

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var repository: GetExchangeRatesRepository

    @Before
    fun setup() {
        hiltRule.inject()
        repository = GetExchangeRatesRepositoryImpl(
            exchangeRatesApi,
            localExchangeRatesRepository,
            apiSyncTimeRepository,
            logger
        )

        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun getLatestCurrencyRate_should_fetch_rates_from_API_and_save_to_local_repository() = runTest {
        // Arrange
        val mockResponse =  ExchangeRatesResponse(
            rates = mapOf("USD" to 1.0, "INR" to 74.5),
            base = "USD",
            disclaimer = "test",
            license = "test",
            timestamp = 1234567890
        )

        val expectedRates = listOf(
            CurrencyRate(currency = "USD", currencyRate = 1.0),
            CurrencyRate(currency = "INR", currencyRate = 74.5)
        )

        coEvery { mockk<ExchangeRatesApi>().getLatestRate() } returns mockResponse
        coEvery { apiSyncTimeRepository.saveSyncTime() } returns Unit
        coEvery { localExchangeRatesRepository.saveExchangeRates(any<List<CurrencyRate>>()) } returns Unit

        // Act
        val result = repository.getLatestCurrencyRate()

        // Assert
        assertEquals(expectedRates, result)
        verify { logger.i("load from API") }
        coVerify { apiSyncTimeRepository.saveSyncTime() }
        coVerify { localExchangeRatesRepository.saveExchangeRates(expectedRates) }
    }
}