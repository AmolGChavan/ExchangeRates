package com.paypay.exchangerates.data.utils.network

import android.net.Network

interface NetworkCapabilitiesProvider {
    fun isNetworkAvailable(network: Network): Boolean
}