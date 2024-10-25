package com.paypay.exchangerates.data.utils

import com.paypay.exchangerates.domain.manager.TimeProvider

class DefaultTimeProvider : TimeProvider {
    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}