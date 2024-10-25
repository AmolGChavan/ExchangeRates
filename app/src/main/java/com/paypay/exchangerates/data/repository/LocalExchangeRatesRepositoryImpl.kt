package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.data.local.ExchangeRatesDao
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import javax.inject.Inject

class LocalExchangeRatesRepositoryImpl @Inject constructor(private val exchangeRatesDao: ExchangeRatesDao): LocalExchangeRatesRepository {
    override suspend fun saveExchangeRates(rates: List<CurrencyRate>) {
        exchangeRatesDao.upsert(rates)
    }

    override suspend fun getCurrencyRates(): List<CurrencyRate> {
       return exchangeRatesDao.getCurrencyRates()
    }
}