package com.paypay.exchangerates.data.utils.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities


class DefaultNetworkCapabilitiesProvider(private val connectivityManager: ConnectivityManager) :
    NetworkCapabilitiesProvider {
    override fun isNetworkAvailable(network: Network): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}