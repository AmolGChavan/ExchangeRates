package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class GetCurrencyExchangeRatesRepositoryImplTest {

    private val exchangeRatesRepository: GetExchangeRatesRepository = mockk()
    private val localExchangeRatesRepository: LocalExchangeRatesRepository = mockk()
    private val repository = GetCurrencyExchangeRatesRepositoryImpl(exchangeRatesRepository, localExchangeRatesRepository)

    @Test
    fun `test getCurrencyExchangeRate when loadFromLocal is true and base currency exists`() = runTest {
        val baseCurrency = CurrencyRate("USD", 1.0)
        val localRates = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85),
            CurrencyRate("JPY", 110.0)
        )
        coEvery { localExchangeRatesRepository.getCurrencyRates() } returns localRates

        val result = repository.getCurrencyExchangeRate(baseCurrency, true)

        val expected = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85 * (1 / 1.0) * 1.0),
            CurrencyRate("JPY", 110.0 * (1 / 1.0) * 1.0)
        )

        assertEquals(expected, result)
    }

    @Test
    fun `test getCurrencyExchangeRate when loadFromLocal is false and base currency exists`() = runTest {
        val baseCurrency = CurrencyRate("USD", 1.0)
        val latestRates = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85),
            CurrencyRate("JPY", 110.0)
        )
        coEvery { exchangeRatesRepository.getLatestCurrencyRate() } returns latestRates

        val result = repository.getCurrencyExchangeRate(baseCurrency, false)

        val expected = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85 * (1 / 1.0) * 1.0),
            CurrencyRate("JPY", 110.0 * (1 / 1.0) * 1.0)
        )

        assertEquals(expected, result)
    }

    @Test
    fun `test getCurrencyExchangeRate when base currency does not exist`() = runTest {
        val baseCurrency = CurrencyRate("GBP", 1.0) // Base currency not in rates
        val localRates = listOf(
            CurrencyRate("USD", 1.0),
            CurrencyRate("EUR", 0.85),
            CurrencyRate("JPY", 110.0)
        )
        coEvery { localExchangeRatesRepository.getCurrencyRates() } returns localRates

        val result = repository.getCurrencyExchangeRate(baseCurrency, true)

        // Expecting the original rates since base currency does not exist
        assertEquals(localRates, result)
    }

    @Test
    fun `test getCurrencyExchangeRate when no rates are returned`() = runTest {
        val baseCurrency = CurrencyRate("USD", 1.0)
        coEvery { localExchangeRatesRepository.getCurrencyRates() } returns emptyList()

        val result = repository.getCurrencyExchangeRate(baseCurrency, true)

        // Expecting an empty list since no rates are returned
        assertEquals(emptyList<CurrencyRate>(), result)
    }
}