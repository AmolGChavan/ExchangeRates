package com.paypay.exchangerates.domain.repository

import com.paypay.exchangerates.domain.CurrencyRate

interface LocalExchangeRatesRepository {
    suspend fun saveExchangeRates(rates: List<CurrencyRate>)
    suspend fun getCurrencyRates(): List<CurrencyRate>
}