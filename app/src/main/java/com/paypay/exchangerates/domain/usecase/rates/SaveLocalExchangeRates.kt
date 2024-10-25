package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import javax.inject.Inject

class SaveLocalExchangeRates @Inject constructor(
    private val localExchangeRatesRepository: LocalExchangeRatesRepository
) {
    suspend operator fun invoke(currencyRates: List<CurrencyRate>) {
        localExchangeRatesRepository.saveExchangeRates(currencyRates)
    }
}