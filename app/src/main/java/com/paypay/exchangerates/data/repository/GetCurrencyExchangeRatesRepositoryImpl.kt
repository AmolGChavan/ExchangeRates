package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetCurrencyExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import com.paypay.exchangerates.utils.round
import javax.inject.Inject

class GetCurrencyExchangeRatesRepositoryImpl @Inject constructor(
    private val exchangeRatesRepository: GetExchangeRatesRepository,
    private val localExchangeRatesRepository: LocalExchangeRatesRepository
) :
    GetCurrencyExchangeRatesRepository {
    override suspend fun getCurrencyExchangeRate(
        baseCurrency: CurrencyRate,
        loadFromLocal: Boolean
    ): List<CurrencyRate> {
        val items = getCurrencyRates(loadFromLocal)
        val actualCurrRateBase = items.firstOrNull { it.currency == baseCurrency.currency }
        return if (actualCurrRateBase != null) {
            items.map { CurrencyRate(it.currency, ((baseCurrency.currencyRate * (1 / actualCurrRateBase.currencyRate)) * it.currencyRate).round(3)) }
        } else items
    }

    private suspend fun getCurrencyRates(loadFromLocal: Boolean) = if (loadFromLocal)
        localExchangeRatesRepository.getCurrencyRates()
    else
        exchangeRatesRepository.getLatestCurrencyRate()
}