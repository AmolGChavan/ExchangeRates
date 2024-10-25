package com.paypay.exchangerates

import com.paypay.exchangerates.data.remote.ExchangeRatesApi
import com.paypay.exchangerates.data.remote.dto.ExchangeRatesResponse
import javax.inject.Inject

class FakeExchangeRatesApi @Inject constructor() : ExchangeRatesApi {
    override suspend fun getLatestRate(apiKey: String): ExchangeRatesResponse {
        return ExchangeRatesResponse(
            rates = mapOf("USD" to 1.0, "INR" to 74.5),
            base = "USD",
            disclaimer = "test",
            license = "test",
            timestamp = 1234567890
        )
    }
}