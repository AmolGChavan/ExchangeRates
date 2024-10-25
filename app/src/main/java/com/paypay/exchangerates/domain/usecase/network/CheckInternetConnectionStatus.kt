package com.paypay.exchangerates.domain.usecase.network

import com.paypay.exchangerates.domain.repository.NetworkRepository
import javax.inject.Inject

class CheckInternetConnectionStatus @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend operator fun invoke(): Boolean {
        return networkRepository.isNetworkAvailable()
    }
}