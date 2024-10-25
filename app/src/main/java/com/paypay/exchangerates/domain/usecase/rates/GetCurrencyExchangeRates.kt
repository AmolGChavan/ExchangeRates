package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetCurrencyExchangeRatesRepository
import javax.inject.Inject

class GetCurrencyExchangeRates @Inject constructor(
    private val getCurrencyExchangeRatesRepository: GetCurrencyExchangeRatesRepository
) {
    suspend operator fun invoke(baseCurrency: CurrencyRate,loadFromLocal:Boolean): List<CurrencyRate> {
        return getCurrencyExchangeRatesRepository.getCurrencyExchangeRate(baseCurrency,loadFromLocal)
    }
}