package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetAPIExchangeRatesTest {

    private lateinit var mockExchangeRatesRepository: GetExchangeRatesRepository
    private lateinit var getAPIExchangeRates: GetAPIExchangeRates

    @Before
    fun setUp() {
        mockExchangeRatesRepository = mockk()
        getAPIExchangeRates = GetAPIExchangeRates(mockExchangeRatesRepository)
    }

    @Test
    fun `invoke should return currency rates from repository`() = runTest {
        val expectedRates = listOf(CurrencyRate("USD", 1.0), CurrencyRate("INR", 84.5))

        coEvery { mockExchangeRatesRepository.getLatestCurrencyRate() } returns expectedRates
        val result = getAPIExchangeRates()
        assertEquals(expectedRates, result)
        coEvery { mockExchangeRatesRepository.getLatestCurrencyRate() }
    }

}