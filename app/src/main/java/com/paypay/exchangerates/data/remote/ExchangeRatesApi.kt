package com.paypay.exchangerates.data.remote

import com.paypay.exchangerates.data.remote.dto.ExchangeRatesResponse
import com.paypay.exchangerates.utils.Constants.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest.json")
   suspend fun getLatestRate(
        @Query("app_id") apiKey: String = API_KEY
    ): ExchangeRatesResponse
}