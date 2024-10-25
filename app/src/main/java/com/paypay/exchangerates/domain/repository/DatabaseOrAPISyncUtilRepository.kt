package com.paypay.exchangerates.domain.repository

interface DatabaseOrAPISyncUtilRepository{
    suspend fun checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute: Int):Boolean
}