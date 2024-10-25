package com.paypay.exchangerates.data.repository

import android.net.ConnectivityManager
import com.paypay.exchangerates.domain.repository.NetworkRepository
import com.paypay.exchangerates.data.utils.network.NetworkCapabilitiesProvider
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val connectivityManager: ConnectivityManager, private val networkCapabilitiesProvider: NetworkCapabilitiesProvider) :
    NetworkRepository {
    override suspend fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        return networkCapabilitiesProvider.isNetworkAvailable(network)
    }
}