package com.paypay.exchangerates.utils

import com.paypay.exchangerates.BuildConfig


object Constants {

    const val USER_SETTINGS = "user_settings"
    const val APP_ENTRY = "app_entry"
    const val DATA_SYNC_TIME = "data_sync_time"
    const val BASE_URL = "https://openexchangerates.org/api/"

    /**
     * API_CALL_FREQUENCY in minutes
     */
    const val API_CALL_FREQUENCY = 30
    val API_KEY = BuildConfig.API_KEY

}