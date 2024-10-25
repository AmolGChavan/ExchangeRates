package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveLocalExchangeRatesTest {

    private lateinit var mockLocalExchangeRatesRepository: LocalExchangeRatesRepository
    private lateinit var saveLocalExchangeRates: SaveLocalExchangeRates

    @Before
    fun setUp() {
        mockLocalExchangeRatesRepository = mockk()
        saveLocalExchangeRates = SaveLocalExchangeRates(mockLocalExchangeRatesRepository)
    }

    @Test
    fun `invoke should call saveExchangeRates on localExchangeRatesRepository`() = runTest {

        val currencyRates = listOf(CurrencyRate("USD", 1.0), CurrencyRate("INR", 83.0))
        coEvery { mockLocalExchangeRatesRepository.saveExchangeRates(currencyRates) } returns Unit
        saveLocalExchangeRates(currencyRates)
        coVerify { mockLocalExchangeRatesRepository.saveExchangeRates(currencyRates) }
    }

}