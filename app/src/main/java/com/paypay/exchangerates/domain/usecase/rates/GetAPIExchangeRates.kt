package com.paypay.exchangerates.domain.usecase.rates

import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import javax.inject.Inject

class GetAPIExchangeRates @Inject constructor(

    private val exchangeRatesRepository: GetExchangeRatesRepository
) {
    suspend operator fun invoke(): List<CurrencyRate> {
        return exchangeRatesRepository.getLatestCurrencyRate()
    }
}