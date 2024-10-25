package com.paypay.exchangerates.data.manager

import com.paypay.exchangerates.domain.manager.Logger
import timber.log.Timber
import javax.inject.Inject

class TimberLogger @Inject constructor(): Logger {

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun e(message: String) {
        Timber.e(message)
    }

    override fun i(message: String) {
        Timber.i(message)
    }

    override fun w(message: String) {
        Timber.w(message)
    }
}