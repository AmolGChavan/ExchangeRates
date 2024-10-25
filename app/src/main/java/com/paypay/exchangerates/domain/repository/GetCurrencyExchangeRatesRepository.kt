package com.paypay.exchangerates.domain.repository

import com.paypay.exchangerates.domain.CurrencyRate

interface GetCurrencyExchangeRatesRepository {
    suspend fun getCurrencyExchangeRate(baseCurrency: CurrencyRate,loadFromLocal:Boolean): List<CurrencyRate>
}