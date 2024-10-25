package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetCurrencyExchangeRatesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCurrencyExchangeRatesTest {

    private val getCurrencyExchangeRatesRepository: GetCurrencyExchangeRatesRepository = mockk()
    private val getCurrencyExchangeRates =
        GetCurrencyExchangeRates(getCurrencyExchangeRatesRepository)

    @Test
    fun `test invoke calls repository and returns rates`() = runTest {
        val baseCurrency = CurrencyRate("USD", 1.0)
        val expectedRates = listOf(
            CurrencyRate("EUR", 0.85),
            CurrencyRate("JPY", 110.0)
        )

        coEvery {
            getCurrencyExchangeRatesRepository.getCurrencyExchangeRate(
                baseCurrency,
                true
            )
        } returns expectedRates


        val result = getCurrencyExchangeRates(baseCurrency, true)


        assertEquals(expectedRates, result)
    }

    @Test
    fun `test invoke with different loadFromLocal value`() = runTest {
        val baseCurrency = CurrencyRate("EUR", 1.0)
        val expectedRates = listOf(
            CurrencyRate("USD", 1.18),
            CurrencyRate("JPY", 130.0)
        )


        coEvery {
            getCurrencyExchangeRatesRepository.getCurrencyExchangeRate(
                baseCurrency,
                false
            )
        } returns expectedRates


        val result = getCurrencyExchangeRates(baseCurrency, false)

        assertEquals(expectedRates, result)
    }
}