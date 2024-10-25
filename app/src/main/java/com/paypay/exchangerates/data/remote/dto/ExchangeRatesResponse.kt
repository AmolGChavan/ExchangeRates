package com.paypay.exchangerates.data.remote.dto

data class ExchangeRatesResponse(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Map<String,Double>,
    val timestamp: Int
)