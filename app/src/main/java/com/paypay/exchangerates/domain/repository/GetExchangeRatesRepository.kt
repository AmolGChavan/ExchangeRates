package com.paypay.exchangerates.domain.repository

import com.paypay.exchangerates.domain.CurrencyRate

interface GetExchangeRatesRepository {

    suspend fun getLatestCurrencyRate(): List<CurrencyRate>

}