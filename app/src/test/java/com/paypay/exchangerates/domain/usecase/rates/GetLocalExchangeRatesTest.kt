package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetLocalExchangeRatesTest {

    private lateinit var mockLocalExchangeRatesRepository: LocalExchangeRatesRepository
    private lateinit var getLocalExchangeRates: GetLocalExchangeRates

    @Before
    fun setUp() {
        mockLocalExchangeRatesRepository = mockk()

        getLocalExchangeRates = GetLocalExchangeRates(mockLocalExchangeRatesRepository)
    }

    @Test
    fun `invoke should return currency rates from local repository`() = runTest {

        val expectedRates = listOf(CurrencyRate("USD", 1.0), CurrencyRate("INR", 84.2))

        coEvery { mockLocalExchangeRatesRepository.getCurrencyRates() } returns expectedRates

        val result = getLocalExchangeRates()

        assertEquals(expectedRates, result)
        coEvery { mockLocalExchangeRatesRepository.getCurrencyRates() }
    }
}