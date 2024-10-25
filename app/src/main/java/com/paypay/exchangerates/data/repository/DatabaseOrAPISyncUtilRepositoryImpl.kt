package com.paypay.exchangerates.data.repository

import com.paypay.exchangerates.domain.manager.TimeProvider
import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import com.paypay.exchangerates.domain.repository.DatabaseOrAPISyncUtilRepository
import com.paypay.exchangerates.domain.repository.NetworkRepository
import javax.inject.Inject

class DatabaseOrAPISyncUtilRepositoryImpl @Inject constructor(
    private val timeProvider: TimeProvider,
    private val aPISyncTimeRepository: APISyncTimeRepository,
    private val networkRepository: NetworkRepository,
) : DatabaseOrAPISyncUtilRepository {

    /**
     * Call this to check if we need to sync data from server or local
     *
     * @param apiCallFrequencyInMinute After how many minutes do we need to sync with the server
     *
     * @return true/false
     * if internet connection not available then return true
     * Otherwise, check the difference between the last sync time and the current time. If the elapsed time is greater than a specified threshold, return true; otherwise, return false.
     */
    override suspend fun checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute: Int): Boolean {
        if (!networkRepository.isNetworkAvailable()) return true
        val lastFetchTime: Long = aPISyncTimeRepository.readSyncTime() ?: 0L
        return lastFetchTime != 0L && (timeProvider.currentTimeMillis() - lastFetchTime < apiCallFrequencyInMinute * 60 * 1000)
    }

}