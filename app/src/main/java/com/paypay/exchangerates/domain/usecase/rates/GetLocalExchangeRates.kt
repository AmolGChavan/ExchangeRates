package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import javax.inject.Inject

class GetLocalExchangeRates @Inject constructor(private val localExchangeRatesRepository: LocalExchangeRatesRepository) {
    suspend operator fun invoke(): List<CurrencyRate> {
        return localExchangeRatesRepository.getCurrencyRates()
    }
}