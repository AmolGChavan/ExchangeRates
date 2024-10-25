package com.paypay.exchangerates.domain.manager

interface TimeProvider {
    fun currentTimeMillis(): Long
}