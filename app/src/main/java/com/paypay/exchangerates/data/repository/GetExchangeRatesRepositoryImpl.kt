package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.data.remote.ExchangeRatesApi
import com.paypay.exchangerates.data.remote.dto.ExchangeRatesResponse
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import com.paypay.exchangerates.utils.round
import javax.inject.Inject

class GetExchangeRatesRepositoryImpl @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val localExchangeRatesRepository: LocalExchangeRatesRepository,
    private val aPISyncTimeRepository: APISyncTimeRepository,
    private val logger: Logger
) : GetExchangeRatesRepository {
    override suspend fun getLatestCurrencyRate(): List<CurrencyRate> {
        val response = exchangeRatesApi.getLatestRate()

        val rates = mapCurrencyRates(response)

        logger.i("load from API")
        aPISyncTimeRepository.saveSyncTime()
        localExchangeRatesRepository.saveExchangeRates(rates)
        return rates

    }

    private fun mapCurrencyRates(response: ExchangeRatesResponse): List<CurrencyRate> {
        return response.rates.map { (key, value) ->
            CurrencyRate(currency = key, currencyRate = value.round(3))
        }
    }
}
